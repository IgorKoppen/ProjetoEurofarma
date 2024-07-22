package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.*;
import br.com.connectfy.EurofarmaCliente.exceptions.EmployeeAlreadyInTrainingException;
import br.com.connectfy.EurofarmaCliente.exceptions.InvalidDateException;
import br.com.connectfy.EurofarmaCliente.exceptions.PasswordDoesntMatchException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.*;
import br.com.connectfy.EurofarmaCliente.repositories.TrainingRepository;
import br.com.connectfy.EurofarmaCliente.util.RandomStringGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {


        private final TrainingRepository trainingRepository;
        private final TagsService tagsService;
        private final InstructorService instructorService;
        private final EmployeeService employeeService;

        public TrainingService(TrainingRepository trainingRepository, TagsService tagsService, InstructorService instructorService, EmployeeService employeeService) {
            this.trainingRepository = trainingRepository;
            this.tagsService = tagsService;
            this.instructorService = instructorService;
            this.employeeService = employeeService;
        }

        @Transactional
        public ResponseEntity<String> create(TrainingCreationDTO trainingDTO) {
            LocalDateTime parsedDate = parseDate(trainingDTO.closingDate());
            validateDateOfClose(parsedDate,"Data de fechamento não pode ser no passado!");
            List<Tag> tags = trainingDTO.tags().stream()
                    .map(this::getTagById)
                    .collect(Collectors.toList());

            List<Instructor> instructors = trainingDTO.instructor().stream()
                    .map(this::getInstructorById)
                    .collect(Collectors.toList());

            LocalDateTime now = LocalDateTime.now();
            String code;
            do {
                code = RandomStringGenerator.generateRoomCode(10);
            } while (trainingRepository.existsByCode(code));


            Training training = new Training();
            training.setName(trainingDTO.name());
            training.setCode(code);
            training.setDescription(trainingDTO.description());
            training.setCreationDate(now);
            training.setClosingDate(parsedDate);
            training.setPassword(RandomStringGenerator.generatePassword(10));
            training.setStatus(true);
            training.setInstructors(instructors);
            training.setTags(tags);

            trainingRepository.save(training);
            return ResponseEntity.ok("Treinamento inserido com sucesso!");
        }

        @Transactional(readOnly = true)
        public TrainingHistoricDTO getById(Long id) {
            Training training = trainingRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
            return convertToTrainingHistoricDTO(training);
        }

        @Transactional(readOnly = true)
        public List<TrainingHistoricDTO> findAll() {
            List<Training> trainings = trainingRepository.findAll();
            return trainings.stream()
                    .map(this::convertToTrainingHistoricDTO)
                    .collect(Collectors.toList());
        }

        @Transactional
        public ResponseEntity<String> update(Long id, TrainingCreationDTO trainingDTO) {
            Training training = trainingRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));

            List<Tag> tags = trainingDTO.tags().stream()
                    .map(this::getTagById)
                    .collect(Collectors.toList());

            List<Instructor> instructors = trainingDTO.instructor().stream()
                    .map(this::getInstructorById)
                    .collect(Collectors.toList());

            LocalDateTime parsedDate = parseDate(trainingDTO.closingDate());
            validateDateOfClose(training.getClosingDate(),"Data de fechamento não pode ser no passado!");
            training.setName(trainingDTO.name());
            training.setDescription(trainingDTO.description());
            training.setClosingDate(parsedDate);
            training.setInstructors(instructors);
            training.setTags(tags);

            trainingRepository.save(training);
            return ResponseEntity.ok("Treinamento atualizado com sucesso!");
        }

        @Transactional
        public ResponseEntity<String> addEmployee(UserConfirmAssinatureDTO userConfirmAssinatureDTO) {
            try {
                Training training = getTrainingByCode(userConfirmAssinatureDTO.code());
                validateDateOfClose(training.getClosingDate(),"Sala já encerrada!");
                validatePassword(training, userConfirmAssinatureDTO.password());
                EmployeeInfoDTO employeeDTO = employeeService.findById(userConfirmAssinatureDTO.userId());
                Employee employee = new Employee(employeeDTO);

                addEmployeeToTraining(training, employee, userConfirmAssinatureDTO.assinatura());

                return ResponseEntity.ok("empregado adicionado com sucesso!");
            } catch (Exception e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        }

        @Transactional(readOnly = true)
        public TrainingHistoricDTO findByCode(String code) {
            Training training = getTrainingByCode(code);
            validateDateOfClose(training.getClosingDate(),"Sala já encerrada!");
            return convertToTrainingHistoricDTO(training);
        }

        @Transactional(readOnly = true)
        public ResponseEntity<String> confirmPassword(Long idUser, String code, String password) {
            Training training = getTrainingByCode(code);
            validatePassword(training, password);
            EmployeeInfoDTO employeeDTO = employeeService.findById(idUser);
            Employee employee = new Employee(employeeDTO);
            validateDateOfClose(training.getClosingDate(),"Sala já encerrada!");
            if (isEmployeeInTraining(employee,training)) {
                throw new EmployeeAlreadyInTrainingException("Você já está no treinamento!");
            }
            return ResponseEntity.ok("Senha correta!");
        }

        @Transactional
        public void delete(Long id) {
            if (!trainingRepository.existsById(id)) {
                throw new ResourceNotFoundException("No records found with id: " + id);
            }
            try {
                trainingRepository.deleteById(id);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException("No records found with id: " + id);
            }
        }

        private Training getTrainingByCode(String code) {
            return trainingRepository.findTrainingByCode(code)
                    .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada: " + code));
        }

        private void validatePassword(Training training, String password) {
            if (!training.getPassword().equals(password)) {
                throw new PasswordDoesntMatchException("Senha incorreta!");
            }
        }
        private void validateDateOfClose(LocalDateTime date, String message) {
        if(date.isBefore(LocalDateTime.now())) {
            throw  new InvalidDateException(message);
        }
        }
        private void addEmployeeToTraining(Training training, Employee employee, String assinatura) {
            if (isEmployeeInTraining(employee,training)) {
                throw new EmployeeAlreadyInTrainingException("Você já está no treinamento!");
            }
            EmployeeTrainingKey key = new EmployeeTrainingKey(employee.getId(), training.getId());
            EmployeeTraining employeeTraining = new EmployeeTraining(key, employee, training, assinatura);

            training.getEmployees().add(employeeTraining);
            employee.getEmployeeTrainings().add(employeeTraining);

            trainingRepository.save(training);
        }



        private boolean isEmployeeInTraining(Employee employee, Training training) {
            return training.getEmployees().stream()
                    .anyMatch(et -> et.getEmployee().equals(employee));
        }

        private TrainingHistoricDTO convertToTrainingHistoricDTO(Training training) {
            return new TrainingHistoricDTO(
                    training.getId(),
                    training.getName(),
                    training.getCode(),
                    training.getCreationDate(),
                    training.getClosingDate(),
                    training.getStatus(),
                    training.getPassword(),
                    training.getDescription(),
                    training.getInstructors().stream()
                            .map(instructor -> new InstructorNameAndIdDTO(
                                    instructor.getId(),
                                    instructor.getEmployee().getName(),
                                    instructor.getEmployee().getSurname(),
                                    instructor.getEmployee().getName() + " " + instructor.getEmployee().getSurname()))
                            .collect(Collectors.toList()),
                    training.getTags(),
                    training.getEmployees()
            );
        }

        private LocalDateTime parseDate(String date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss,SSS");
            return LocalDateTime.parse(date, formatter);
        }

        private Tag getTagById(Long id) {
            TagDTO tagDTO = tagsService.getById(id);
            return new Tag(tagDTO.id(), tagDTO.name(), tagDTO.color(), tagDTO.trainings());
        }

        private Instructor getInstructorById(Long id) {
            InstructorDTO instructorDTO = instructorService.getById(id);
            return new Instructor(instructorDTO.id(), instructorDTO.employee(), instructorDTO.trainnings());
        }
    }