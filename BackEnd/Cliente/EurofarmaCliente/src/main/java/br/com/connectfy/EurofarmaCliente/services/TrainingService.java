package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.*;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingInsertDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {


    private final TrainingRepository trainingRepository;
    private final TagService tagsService;
    private final InstructorService instructorService;
    private final EmployeeService employeeService;
    private final MessageService messageService;

    public TrainingService(TrainingRepository trainingRepository,
                           TagService tagsService,
                           InstructorService instructorService,
                           EmployeeService employeeService,
                           MessageService messageService,
                           DepartmentService departmentService) {
        this.trainingRepository = trainingRepository;
        this.tagsService = tagsService;
        this.instructorService = instructorService;
        this.employeeService = employeeService;
        this.messageService = messageService;
    }

    @Transactional
    public ResponseEntity<String> create(TrainingInsertDTO trainingDTO) {
        validateDateOfClose(trainingDTO.getClosingDate(), "Data de fechamento não pode ser no passado!");
        List<Tag> tags = trainingDTO.getTags().stream()
                .map(tagDTO -> getTagById(tagDTO.getId()))
                .collect(Collectors.toList());

        List<Instructor> instructors = trainingDTO.getInstructor().stream()
                .map(instructorDTO -> getInstructorById(instructorDTO.getId()))
                .collect(Collectors.toList());

        LocalDateTime now = LocalDateTime.now();
        String code;
        do {
            code = RandomStringGenerator.generateRoomCode(10);
        } while (trainingRepository.existsByCode(code));
        Training training = buildTraining(trainingDTO,now, trainingDTO.getClosingDate(), tags, instructors, code);
        Training trainingSaved = trainingRepository.save(training);
        messageToAllEmployeesOfDepartaments("Eurofarma: Código para participar da sala " + trainingDTO.getName() + ". Código: " + code, trainingSaved.getEmployees().stream().map(employeeTraining -> employeeTraining.getEmployee().getCellphoneNumber()).toList());
        return ResponseEntity.ok("Treinamento inserido com sucesso!");
    }

    @Transactional(readOnly = true)
    public TrainingDTO findById(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return convertToTrainingHistoricDTO(training);
    }

    @Transactional(readOnly = true)
    public List<TrainingDTO> findAll() {
        List<Training> trainings = trainingRepository.findAll();
        return trainings.stream()
                .map(this::convertToTrainingHistoricDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<String> update(Long id, TrainingInsertDTO trainingDTO) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));

        List<Tag> tags = trainingDTO.getTags().stream()
                .map(tagDTO -> getTagById(tagDTO.getId()))
                .collect(Collectors.toList());

        List<Instructor> instructors = trainingDTO.getInstructor().stream()
                .map(instructorDTO -> getInstructorById(instructorDTO.getId()))
                .collect(Collectors.toList());

        validateDateOfClose(training.getClosingDate(), "Data de fechamento não pode ser no passado!");
        training.setName(trainingDTO.getName());
        training.setDescription(trainingDTO.getDescription());
        training.setClosingDate(trainingDTO.getClosingDate());
        training.setInstructors(instructors);
        training.setTags(tags);

        trainingRepository.save(training);
        return ResponseEntity.ok("Treinamento atualizado com sucesso!");
    }

    @Transactional
    public ResponseEntity<String> addEmployeeInTraining(UserConfirmAssinatureDTO userConfirmAssinatureDTO) {
        try {
            Training training = getTrainingByCode(userConfirmAssinatureDTO.code());
            validateDateOfClose(training.getClosingDate(), "Sala já encerrada!");
            validatePassword(training, userConfirmAssinatureDTO.password());
            EmployeeDTO employeeDTO = employeeService.findById(userConfirmAssinatureDTO.userId());
            Employee employee = new Employee(employeeDTO);

            addEmployeeToTraining(training, employee, userConfirmAssinatureDTO.assinatura());

            return ResponseEntity.ok("empregado adicionado com sucesso!");
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
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

    @Transactional(readOnly = true)
    public TrainingDTO findByCode(String code) {
        Training training = getTrainingByCode(code);
        validateDateOfClose(training.getClosingDate(), "Sala já encerrada!");
        return convertToTrainingHistoricDTO(training);
    }

    @Transactional(readOnly = true)
    public List<RoomParticipantsDTO> findAllRoomParticipants(Long trainingId) {
        Training training = trainingRepository.findById(trainingId).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + trainingId));
        List<RoomParticipantsDTO> participants = new ArrayList<>();
        for (EmployeeTraining employeeTraining : training.getEmployees()) {
            String fullName = employeeTraining.getEmployee().getName() + " " + employeeTraining.getEmployee().getSurname();
            RoomParticipantsDTO participantDTO = new RoomParticipantsDTO(fullName);
            participants.add(participantDTO);
        }
        return participants;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<String> confirmPassword(Long idUser, String code, String password) {
        Training training = getTrainingByCode(code);
        validatePassword(training, password);
        EmployeeDTO employeeDTO = employeeService.findById(idUser);
        Employee employee = new Employee(employeeDTO);
        validateDateOfClose(training.getClosingDate(), "Sala já encerrada!");
        if (isEmployeeInTraining(employee, training)) {
            throw new EmployeeAlreadyInTrainingException("Você já está no treinamento!");
        }
        return ResponseEntity.ok("Senha correta!");
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
        if (date.isBefore(LocalDateTime.now())) {
            throw new InvalidDateException(message);
        }
    }

    private void addEmployeeToTraining(Training training, Employee employee, String assinatura) {
        if (isEmployeeInTraining(employee, training)) {
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

    private TrainingDTO convertToTrainingHistoricDTO(Training training) {
        return new TrainingDTO(training);
    }

    private void messageToAllEmployeesOfDepartaments(String message,List<String> phoneNumbers) {
        for (String phoneNumber : phoneNumbers) {
            messageService.send(message, phoneNumber);
        }
    }

    private LocalDateTime parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss,SSS");
        return LocalDateTime.parse(date, formatter);
    }

    private Tag getTagById(Long id) {
        TagInfoDTO tagDTO = tagsService.getById(id);
        return new Tag(tagDTO);
    }

    private Instructor getInstructorById(Long id) {
        InstructorDTO instructorDTO = instructorService.findById(id);
        return new Instructor(instructorDTO);
    }

    private Training buildTraining(TrainingInsertDTO trainingDTO, LocalDateTime nowDate, LocalDateTime parsedDate, List<Tag> tags, List<Instructor> instructors, String code) {
        Training training = new Training();
        training.setName(trainingDTO.getName());
        training.setCode(code);
        training.setCreationDate(nowDate);
        training.setDescription(trainingDTO.getDescription());
        training.setCreationDate(LocalDateTime.now());
        training.setClosingDate(parsedDate);
        training.setPassword(RandomStringGenerator.generatePassword(10));
        training.setStatus(true);
        training.setInstructors(instructors);
        training.setTags(tags);
        return training;
    }
}