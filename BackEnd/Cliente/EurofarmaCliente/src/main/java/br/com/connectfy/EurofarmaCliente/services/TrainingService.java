package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.*;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingOfEmployeeDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingWithEmployeesInfo;
import br.com.connectfy.EurofarmaCliente.exceptions.*;
import br.com.connectfy.EurofarmaCliente.models.*;
import br.com.connectfy.EurofarmaCliente.repositories.EmployeeRepository;
import br.com.connectfy.EurofarmaCliente.repositories.TrainingRepository;
import br.com.connectfy.EurofarmaCliente.util.RandomStringGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrainingService {


    private final TrainingRepository trainingRepository;
    private final TagService tagsService;
    private final InstructorService instructorService;
    private final EmployeeService employeeService;
    private final MessageService messageService;
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public TrainingService(TrainingRepository trainingRepository, TagService tagsService, InstructorService instructorService, EmployeeService employeeService, MessageService messageService, EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.trainingRepository = trainingRepository;
        this.tagsService = tagsService;
        this.instructorService = instructorService;
        this.employeeService = employeeService;
        this.messageService = messageService;
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    @Transactional
    public TrainingDTO insert(TrainingInsertDTO trainingDTO) {
        validateDateOfClose(trainingDTO.getClosingDate(), "Data de fechamento não pode ser no passado!");
        Set<Tag> tags = trainingDTO.getTags().stream()
                .map(tagDTO -> getTagById(tagDTO.getId()))
                .collect(Collectors.toSet());

        Set<Instructor> instructors = trainingDTO.getInstructor().stream()
                .map(instructorDTO -> getInstructorById(instructorDTO.getId()))
                .collect(Collectors.toSet());
        LocalDateTime now = LocalDateTime.now();

        String code;
        do {
            code = RandomStringGenerator.generateRoomCode(10);
        } while (trainingRepository.existsByCode(code));

        Training training = buildTraining(trainingDTO,now, trainingDTO.getClosingDate(), tags, instructors, code);
        Training trainingSaved = trainingRepository.save(training);

        if(trainingDTO.getDepartmentIdsToSendMessage() != null){
            Set<String> employeePhoneNumbersByDepartmentIds = departmentService.findAllEmployeePhoneNumbersByDepartmentIds(trainingDTO.getDepartmentIdsToSendMessage());
            messageToAllEmployeesOfDepartaments("Eurofarma: Participe do treinamento '" + trainingDTO.getName() + "' usando o código: " + code, employeePhoneNumbersByDepartmentIds);
        }
        return toDTO(trainingSaved);
    }

    @Transactional
    public TrainingDTO update(Long id, TrainingInsertDTO trainingDTO) {

        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));

        Set<Tag> tags = trainingDTO.getTags().stream()
                .map(tagDTO -> getTagById(tagDTO.getId()))
                .collect(Collectors.toSet());

       Set<Instructor> instructors = trainingDTO.
               getInstructor().stream().map(instructorDTO -> getInstructorById(instructorDTO.getId())).collect(Collectors.toSet());


        validateDateOfClose(training.getClosingDate(), "Sala já encerrada, não pode ser modificada!");

        if(!training.getEmployees().isEmpty()){
            throw new TrainingHasEmployeesException("Não é possível alterar o treinamento com funcionários alocados!");
        }

        instructors.forEach(instructor -> {
            if(training.getEmployees().stream().anyMatch(employee -> employee.getEmployee().equals(instructor.getEmployee()))){
                throw new TrainingHasEmployeesException("Você é um instrutor do treino" + instructor.getEmployee().getName() + " " +  instructor.getEmployee().getSurname());
            }
        });

        training.setName(trainingDTO.getName());
        training.setDescription(trainingDTO.getDescription());
        training.setClosingDate(trainingDTO.getClosingDate());
        training.setTags(tags);

        Training entity = trainingRepository.save(training);
        return toDTO(entity);
    }

    @Transactional(readOnly = true)
    public TrainingDTO findById(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return toDTO(training);
    }

    @Transactional(readOnly = true)
    public List<TrainingDTO> findAll() {
        List<Training> trainings = trainingRepository.findAll();
        return trainings.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }



    @Transactional
    public void addEmployeeInTraining(UserConfirmAssinatureDTO userConfirmAssinatureDTO) {
        try {
            Training training = getTrainingByCode(userConfirmAssinatureDTO.code());
            validateDateOfClose(training.getClosingDate(), "Sala já encerrada!");
            validatePassword(training, userConfirmAssinatureDTO.password());
            Employee employee = employeeRepository.findById(userConfirmAssinatureDTO.userId()).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + userConfirmAssinatureDTO.userId()));

            addEmployeeToTraining(training, employee, userConfirmAssinatureDTO.signature());
            trainingRepository.save(training);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }


    @Transactional
    public void cancelTraining(Long id) {
      Training training = trainingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        validateDateOfClose(training.getClosingDate(), "Sala já encerrada!");
        if(!training.getEmployees().isEmpty()){
            throw new TrainingHasEmployeesException("Não é possível cancelar o treinamento com funcionários alocados!");
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
        return toDTO(training);
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
    public List<TrainingOfEmployeeDTO> findEmployeeTrainingsById(Long idEmployee) {
        List<EmployeeTraining> trainings = trainingRepository.findEmployeeTrainingSortedByRegistrationDateById(idEmployee)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado funcionário com id: " + idEmployee));
        if(trainings.isEmpty()){
            return new ArrayList<>();
        }
        return trainings.stream()
                .map(TrainingOfEmployeeDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TrainingWithEmployeesInfo> findInstructorTrainingsById(Long id) {
        List<Training> trainings = trainingRepository.findByIdInstructorTrainingsSortedByCreationDate(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        if (trainings.isEmpty()) {
            return new ArrayList<>();
        }
        return trainings.stream()
                .map(TrainingWithEmployeesInfo::new
                ).sorted(Comparator.comparing(TrainingWithEmployeesInfo::getClosingDate).reversed())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public void confirmPassword(Long idUser, String code, String password) {
        Training training = getTrainingByCode(code);
        validatePassword(training, password);
        EmployeeInfoDTO employeeDTO = employeeService.findById(idUser);
        Employee employee = new Employee(employeeDTO);
        validateDateOfClose(training.getClosingDate(), "Sala já encerrada!");
        if (isEmployeeInTraining(employee, training)) {
            throw new EmployeeAlreadyInTrainingException("Você já está no treinamento!");
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
        if (date.isBefore(LocalDateTime.now())) {
            throw new InvalidDateException(message);
        }
    }

    private void addEmployeeToTraining(Training training, Employee employee, String signature) {
        if (isEmployeeInTraining(employee, training)) {
            throw new EmployeeAlreadyInTrainingException("Você já está no treinamento!");
        }
        if(IsEmployeeAnInstructor(employee,training)){
            throw new EmployeeAlreadyInTrainingException("Você é um instrutor da sala!");
        }
        EmployeeTrainingKey key = new EmployeeTrainingKey(employee.getId(), training.getId());
        EmployeeTraining employeeTraining = new EmployeeTraining(key, employee, training, signature);

        training.getEmployees().add(employeeTraining);
        employee.getEmployeeTrainings().add(employeeTraining);
    }


    private boolean isEmployeeInTraining(Employee employee, Training training) {
        return training.getEmployees().stream()
                .anyMatch(et -> et.getEmployee().equals(employee));
    }
    private boolean IsEmployeeAnInstructor(Employee employee, Training training){
        return training.getInstructors()
                .stream()
                .anyMatch(
                        instructor -> instructor.getEmployee()
                                .equals(employee));
    }

    private void messageToAllEmployeesOfDepartaments(String message,Set<String> phoneNumbers) {
        for (String phoneNumber : phoneNumbers) {
            messageService.send(message, phoneNumber);
        }
    }

    private Tag getTagById(Long id) {
        TagDTO tagDTO = tagsService.getById(id);
        return new Tag(tagDTO);
    }

    private Instructor getInstructorById(Long id) {
        InstructorDTO instructorDTO = instructorService.findById(id);
        return new Instructor(instructorDTO);
    }

    private TrainingDTO toDTO(Training training) {
        return new TrainingDTO(training);
    }

    private Training buildTraining(TrainingInsertDTO trainingDTO, LocalDateTime nowDate, LocalDateTime parsedDate, Set<Tag> tags, Set<Instructor> instructors, String code) {
        Training training = new Training();
        training.setName(trainingDTO.getName());
        training.setCode(code);
        training.setCreationDate(nowDate);
        training.setDescription(trainingDTO.getDescription());
        training.setCreationDate(LocalDateTime.now());
        training.setClosingDate(parsedDate);
        training.setPassword(RandomStringGenerator.generatePassword(10));
        training.setInstructors(instructors);
        training.setTags(tags);
        return training;
    }
}