package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.*;
import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDetailsDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.*;
import br.com.connectfy.EurofarmaCliente.exceptions.*;
import br.com.connectfy.EurofarmaCliente.models.*;
import br.com.connectfy.EurofarmaCliente.repositories.EmployeeRepository;
import br.com.connectfy.EurofarmaCliente.repositories.QuizRepository;
import br.com.connectfy.EurofarmaCliente.repositories.TrainingRepository;
import br.com.connectfy.EurofarmaCliente.specification.SearchCriteria;
import br.com.connectfy.EurofarmaCliente.specification.TrainingSpecification;
import br.com.connectfy.EurofarmaCliente.util.RandomStringGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
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
        LocalDateTime parsedDate = parseDate(trainingDTO.getClosingDate());
        validateDateOfClose(parsedDate, "Data de fechamento não pode ser no passado!");

        Set<Tag> tags = trainingDTO.getTags().stream()
                .map(tagDTO -> getTagById(tagDTO.getId()))
                .collect(Collectors.toSet());

        Set<Instructor> instructors = trainingDTO.getInstructor().stream()
                .map(instructorDTO -> getInstructorById(instructorDTO.getId()))
                .collect(Collectors.toSet());
        LocalDateTime now = LocalDateTime.now();

        Set<Department> departments = trainingDTO.getDepartment().stream()
                .map(departmentDTO -> getDepartmentById(departmentDTO.getId()))
                .collect(Collectors.toSet());

        String code;


        do {
            code = RandomStringGenerator.generateRoomCode(10);
        } while (trainingRepository.existsByCode(code));

        Training training = buildTraining(trainingDTO, now, parsedDate, tags, instructors, code, departments);

        if (Boolean.TRUE.equals(trainingDTO.getHasQuiz()) && trainingDTO.getQuiz() != null) {
            training.setQuiz(new Quiz(trainingDTO.getQuiz()));
        }

        Training trainingSaved = trainingRepository.save(training);

        if (trainingDTO.getToSendMessage()) {
            Set<Long> departmentIds = trainingDTO.getDepartment().stream().map(DepartmentDTO::getId).collect(Collectors.toSet());
            Set<String> employeePhoneNumbersByDepartmentIds = departmentService.findAllEmployeePhoneNumbersByDepartmentIds(departmentIds);
            messageToAllEmployeesOfDepartments("Eurofarma: Participe do treinamento '" + trainingDTO.getName() + "' usando o código: " + code, employeePhoneNumbersByDepartmentIds);
        }
        return toDTO(trainingSaved);
    }

    @Transactional
    public TrainingDTO update(Long id, TrainingInsertDTO trainingDTO) {



        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treinamento não encontrada com id: " + id));

        Set<Tag> tags = trainingDTO.getTags().stream()
                .map(tagDTO -> getTagById(tagDTO.getId()))
                .collect(Collectors.toSet());

        Set<Instructor> instructors = trainingDTO.
                getInstructor().stream().map(instructorDTO -> getInstructorById(instructorDTO.getId())).collect(Collectors.toSet());

        Set<Department> departments = trainingDTO.getDepartment().stream()
                .map(departmentDTO -> getDepartmentById(departmentDTO.getId()))
                .collect(Collectors.toSet());

        LocalDateTime parsedDate = parseDate(trainingDTO.getClosingDate());
        validateDateOfClose(parsedDate, "Lista já encerrada, não pode ser modificada!");

        if (!training.getEmployees().isEmpty()) {
            throw new TrainingHasEmployeesException("Não é possível alterar o treinamento com funcionários alocados!");
        }

        instructors.forEach(instructor -> {
            if (training.getEmployees().stream().anyMatch(employee -> employee.getEmployee().equals(instructor.getEmployee()))) {
                throw new TrainingHasEmployeesException("Você é um instrutor do treino" + instructor.getEmployee().getName() + " " + instructor.getEmployee().getSurname());
            }
        });

        if (trainingDTO.getToSendMessage()) {
            Set<Long> departmentIds = trainingDTO.getDepartment().stream().map(DepartmentDTO::getId).collect(Collectors.toSet());
            Set<String> employeePhoneNumbersByDepartmentIds = departmentService.findAllEmployeePhoneNumbersByDepartmentIds(departmentIds);
            messageToAllEmployeesOfDepartments("Eurofarma: Participe do treinamento '" + trainingDTO.getName() + "' usando o código: " + training.getCode(), employeePhoneNumbersByDepartmentIds);
        }

        training.setName(trainingDTO.getName());
        training.setDescription(trainingDTO.getDescription());
        training.setClosingDate(parsedDate);
        training.setTags(tags);
        training.setDepartments(departments);
        training.setHasQuiz(trainingDTO.getHasQuiz());
        training.setInstructors(instructors);

        if (Boolean.TRUE.equals(trainingDTO.getHasQuiz()) && trainingDTO.getQuiz() != null) {
            training.setQuiz(new Quiz(trainingDTO.getQuiz()));
        } else {
            training.setQuiz(null);
        }


        Training entity = trainingRepository.save(training);
        return toDTO(entity);
    }

    @Transactional(readOnly = true)
    public TrainingDTO findById(Long id,DateTimeFormatter formatter) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return new TrainingDTO(training,formatter);
    }

    @Transactional(readOnly = true)
    public Page<TrainingDTO> findWithPagination(Pageable pageable,DateTimeFormatter formatter) {
        Page<Training> trainingPage = trainingRepository.findAll(pageable);
        return trainingPage.map(trainingDTO -> new TrainingDTO(trainingDTO, formatter));
    }

    @Transactional
    public void addEmployeeInTraining(UserConfirmAssinatureDTO userConfirmAssinatureDTO) {
        try {
            Training training = getTrainingByCode(userConfirmAssinatureDTO.code());
            validateDateOfClose(training.getClosingDate(), "Lista já encerrada!");
            validatePassword(training, userConfirmAssinatureDTO.password());

            Employee employee = employeeRepository.findById(userConfirmAssinatureDTO.userId()).orElseThrow(() -> new ResourceNotFoundException("Treinamento não encontrada com id: " + userConfirmAssinatureDTO.userId()));


            addEmployeeToTraining(training, employee, userConfirmAssinatureDTO.signature(), userConfirmAssinatureDTO.quizTries(), userConfirmAssinatureDTO.nota());
            trainingRepository.save(training);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Transactional
    public void cancelTraining(Long id) {
        Training training = trainingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Treinamento não encontrada com id: " + id));
        if (!training.getEmployees().isEmpty()) {
            throw new TrainingHasEmployeesException("Não é possível cancelar o treinamento com funcionários alocados!");
        }

        try {
            trainingRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
    }

    @Transactional(readOnly = true)
    public TrainingDTO findByCode(Long employeeId,String code) {
        Employee employee = getEmployeeById(employeeId);
        Training training = getTrainingByCode(code);
        validateEmployeeForTraining(employee,training);
        validateDateOfClose(training.getClosingDate(), "Lista já encerrada!");
        return toDTO(training);
    }

    @Transactional(readOnly = true)
    public List<RoomParticipantsDTO> findAllRoomParticipants(Long trainingId) {
        Training training = trainingRepository.findById(trainingId).orElseThrow(() -> new ResourceNotFoundException("Treinamento não encontrada com id: " + trainingId));
        List<RoomParticipantsDTO> participants = new ArrayList<>();
        for (EmployeeTraining employeeTraining : training.getEmployees()) {
            String fullName = employeeTraining.getEmployee().getName() + " " + employeeTraining.getEmployee().getSurname();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss,SSS");
            String registrationFormat = formatter.format(employeeTraining.getRegistrationDate());
            RoomParticipantsDTO participantDTO = new RoomParticipantsDTO(fullName, employeeTraining.getEmployee().getEmployeeRegistration(), registrationFormat);
            participants.add(participantDTO);
        }
        return participants;
    }



    @Transactional(readOnly = true)
    public List<TrainingOfEmployeeDTO> findEmployeeTrainingsById(Long idEmployee) {
        List<EmployeeTraining> trainings = trainingRepository.findEmployeeTrainingSortedByRegistrationDateById(idEmployee)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrada com id: " + idEmployee));
        if (trainings.isEmpty()) {
            return new ArrayList<>();
        }
        return trainings.stream()
                .map(TrainingOfEmployeeDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TrainingWithEmployeesInfo> findInstructorTrainingsById(Long id) {
        List<Training> trainings = trainingRepository.findByIdInstructorTrainingsSortedByCreationDate(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treinador não encontrada com id: " + id));
        if (trainings.isEmpty()) {
            return new ArrayList<>();
        }
        return trainings.stream()
                .map(TrainingWithEmployeesInfo::new
                ).sorted(Comparator.comparing(TrainingWithEmployeesInfo::getClosingDate).reversed())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TrainingWithEmployeesInfo> findTrainingByIdAndTag(Long id, String tagName) {
        List<Training> trainings = trainingRepository.findByIdInstructorTrainingsByTagSortedByCreationDate(id, tagName)
                .orElseThrow(() -> new ResourceNotFoundException("Treinador não encontrada com id: " + id));

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

    @Transactional(readOnly = true)
    public Page<TrainingDTO> search(List<SearchCriteria> params, Pageable pageable) {
        Specification<Training> specification = Specification.where(null);

        for (SearchCriteria criteria : params) {
            specification = specification.and(new TrainingSpecification(criteria));
        }
        Page<Training> trainingPage = trainingRepository.findAll(specification, pageable);
        return trainingPage.map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public TrainingDetailsDTO findTrainingInfoById(Long trainingId) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new ResourceNotFoundException("Treinamento não encontrado com id: " + trainingId));

        List<InstructorDetailsDTO> instructors = training.getInstructors().stream()
                .map(instructor -> {
                    Employee employee = instructor.getEmployee();
                    return new InstructorDetailsDTO(
                            employee.getName(),
                            employee.getSurname(),
                            employee.getEmployeeRegistration()
                    );
                })
                .collect(Collectors.toList());

        List<EmployeeTrainingInfoDTO> attendanceList = training.getEmployeeTrainings().stream()
                .map(employeeTraining -> {
                    EmployeeTrainingInfoDTO dto = new EmployeeTrainingInfoDTO();
                    Employee employee = employeeTraining.getEmployee();
                    dto.setId(employee.getId());
                    dto.setName(employee.getName());
                    dto.setSurname(employee.getSurname());
                    dto.setEmployeeRegistration(employee.getEmployeeRegistration());
                    dto.setSignature(employeeTraining.getSignature());
                    dto.setRegistrationDate(employeeTraining.getRegistrationDate());
                    dto.setQuizTries(employeeTraining.getQuizTries());
                    dto.setNota(employeeTraining.getNota());
                    return dto;
                })
                .collect(Collectors.toList());

        TrainingDetailsDTO response = new TrainingDetailsDTO();
        response.setInstructors(instructors);
        response.setAttendanceList(attendanceList);

        return response;
    }



    private void validatePassword(Training training, String password) {
        if (!training.getPassword().equals(password)) {
            throw new PasswordDoesntMatchException("Senha incorreta!");
        }
    }

    private LocalDateTime parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss,SSSSSSSSS");
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Formato incorreto da data: " + date + " Formato correto: dd/MM/yyyy HH:mm:ss,SSSSSSSSS");
        }
    }


    private void validateDateOfClose(LocalDateTime date, String message) {
        if (date.isBefore(LocalDateTime.now())) {
            throw new InvalidDateException(message);
        }
    }

    private void addEmployeeToTraining(Training training, Employee employee, String signature, Integer quizTries, Double nota) {
        validateEmployeeForTraining(employee,training);
        EmployeeTrainingKey key = new EmployeeTrainingKey(employee.getId(), training.getId());
        EmployeeTraining employeeTraining = new EmployeeTraining(key, employee, training, signature);
        if(quizTries != null) {
            employeeTraining.setQuizTries(quizTries);
        }
        if(nota != null) {
            employeeTraining.setNota(nota);
        }

        training.getEmployees().add(employeeTraining);
        employee.getEmployeeTrainings().add(employeeTraining);
    }

    private void validateEmployeeForTraining(Employee employee, Training training) {
        if(isNotEmployeeInDepartment(employee, training)){
            throw new EmployeeNotInDepartmentException("Seu departamento não tem acesso a esse treinamento!");
        }
        if (isEmployeeInTraining(employee, training)) {
            throw new EmployeeAlreadyInTrainingException("Você já está no treinamento!");
        }
        if (isEmployeeAnInstructor(employee, training)) {
            throw new EmployeeAlreadyInTrainingException("Você é um instrutor da sala!");
        }
    }


    private boolean isEmployeeInTraining(Employee employee, Training training) {
        return training.getEmployees().stream()
                .anyMatch(et -> et.getEmployee().equals(employee));
    }

    private boolean isEmployeeAnInstructor(Employee employee, Training training) {
        return training.getInstructors()
                .stream()
                .anyMatch(
                        instructor -> instructor.getEmployee()
                                .equals(employee));
    }

    private boolean isNotEmployeeInDepartment(Employee employee, Training training) {
        return training.getDepartments().stream().noneMatch(department -> department.getRoles().stream().anyMatch(role -> role.getEmployees().contains(employee)));
    }

    private void messageToAllEmployeesOfDepartments(String message, Set<String> phoneNumbers) {
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

    private Department getDepartmentById(Long id) {
        DepartmentInfoDTO departmentDTO = departmentService.findById(id);
        return new Department(departmentDTO);
    }

    private Training getTrainingByCode(String code) {
        return trainingRepository.findTrainingByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada: " + code));
    }
    private Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + id));
    }


    private Training buildTraining(TrainingInsertDTO trainingDTO, LocalDateTime nowDate, LocalDateTime parsedDate, Set<Tag> tags, Set<Instructor> instructors, String code, Set<Department> departments) {
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
        training.setDepartments(departments);
        training.setHasQuiz(trainingDTO.getHasQuiz());
        return training;
    }

    private TrainingDTO toDTO(Training training) {
        return new TrainingDTO(training);
    }


}