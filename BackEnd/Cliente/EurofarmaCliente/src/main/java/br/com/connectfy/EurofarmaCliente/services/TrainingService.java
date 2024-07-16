package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.*;
import br.com.connectfy.EurofarmaCliente.exceptions.EmployeeAlreadyInTrainning;
import br.com.connectfy.EurofarmaCliente.exceptions.PasswordDontMatch;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Instructor;
import br.com.connectfy.EurofarmaCliente.models.Tag;
import br.com.connectfy.EurofarmaCliente.models.Training;
import br.com.connectfy.EurofarmaCliente.repositories.TrainningRepository;
import jakarta.persistence.EntityManager;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

@Service
public class TrainingService {

    @Autowired
    private TrainningRepository trainningRepository;
    @Autowired
    private InstructorService instructorService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private EmployeeService employeeService;


    @Transactional
    public ResponseEntity<String> create(TrainingCreationDTO trainingDTO) {
        List<Tag> tags = trainingDTO.tags().stream()
                .map(id -> {
                    TagDTO tagDTO = tagsService.getById(id);
                    return new Tag(tagDTO.id(), tagDTO.name(),tagDTO.color(),tagDTO.trainings());
                })
                .collect(Collectors.toList());

        List<Instructor> instructors = trainingDTO.instructor().stream()
                .map(id -> {
                    InstructorDTO instructorDTO = instructorService.getById(id);
                    return new Instructor(instructorDTO.id(), instructorDTO.employee(), instructorDTO.trainnings());
                })
                .collect(Collectors.toList());


        LocalDateTime now = LocalDateTime.now();
        Training trainning = new Training();
        trainning.setName(trainingDTO.name());
        trainning.setCode(generatePassword(2));
        trainning.setDescription(trainingDTO.description());
        trainning.setCreationDate(now);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss,SSS");
        LocalDateTime parsedDate = LocalDateTime.parse(trainingDTO.closingDate(), formatter);
        trainning.setClosingDate(parsedDate);
        trainning.setPassword(generatePassword(1));
        trainning.setStatus(true);
        trainning.setInstructors(instructors);
        trainning.setTags(tags);
        trainningRepository.save(trainning);
        return ResponseEntity.ok("Treinamento inserido com sucesso!");
    }

    @Transactional(readOnly = true)
    public TrainingHistoricDTO getById(Long id) {
        Training trainning = trainningRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return new TrainingHistoricDTO(trainning.getId(), trainning.getName(), trainning.getCode(),
                trainning.getCreationDate(),trainning.getClosingDate(),trainning.getStatus(),
                trainning.getPassword(),trainning.getDescription(),trainning.getInstructors().stream().map(instructor -> instructor.getEmployee().getName()).collect(Collectors.toList()), trainning.getTags(), trainning.getEmployees());
    }
    @Transactional(readOnly = true)
    public List<TrainingHistoricDTO> findAll() {
        List<Training> trainnings = trainningRepository.findAll();
        return trainnings.stream().map(trainning
                        ->  new TrainingHistoricDTO(trainning.getId(), trainning.getName(), trainning.getCode(),
                trainning.getCreationDate(),trainning.getClosingDate(),trainning.getStatus(),
                trainning.getPassword(),trainning.getDescription(),trainning.getInstructors().stream().map(instructor -> instructor.getEmployee().getName()).collect(Collectors.toList()), trainning.getTags(), trainning.getEmployees()))
                .collect(Collectors.toList());
    }
    @Transactional
    public ResponseEntity<String> update(Long idd, TrainingCreationDTO trainningDTO) {
        Training updateTrainning= trainningRepository.findById(idd).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + idd));
        List<Tag> tags = trainningDTO.tags().stream()
                .map(id -> {
                    TagDTO tagDTO = tagsService.getById(id);
                    return new Tag(tagDTO.id(), tagDTO.name(),tagDTO.color(),tagDTO.trainings());
                }).collect(Collectors.toList());

        List<Instructor> instructors = trainningDTO.instructor().stream()
                .map(idInstructor -> {
                    InstructorDTO instructorDTO = instructorService.getById(idInstructor);
                    return new Instructor(instructorDTO.id(), instructorDTO.employee(), instructorDTO.trainnings());
                }).collect(Collectors.toList());

        updateTrainning.setName(trainningDTO.name());
        updateTrainning.setDescription(trainningDTO.description());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss,SSS");
        LocalDateTime parsedDate = LocalDateTime.parse(trainningDTO.closingDate(), formatter);
        updateTrainning.setClosingDate(parsedDate);
        updateTrainning.setInstructors(instructors);
        updateTrainning.setTags(tags);
        trainningRepository.save(updateTrainning);
        return ResponseEntity.ok("Treinamento atualizado com sucesso!");
    }

    @Transactional
    public ResponseEntity<?> addEmployee(String code,String password, Long id) {
        try {
            Training trainning = trainningRepository.findTrainingByCode(code);
            if(!trainning.getPassword().equals(password)) {
                throw new PasswordDontMatch("Senha Incorreta!");
            }
            EmployeeInfoDTO employeeDTO = employeeService.findById(id);
            Employee employee = new Employee(employeeDTO);
            if(!trainning.getEmployees().contains(employee)) {
                trainning.getEmployees().add(employee);
                trainningRepository.save(trainning);
                return ResponseEntity.ok("Empregado adicionado com sucesso no treinamento!");
            } else {
                throw new EmployeeAlreadyInTrainning("Empregado já está nesse treinamento");
            }
        }catch (Exception e){
            throw new ResourceNotFoundException("No records found with code: " + code);
        }
    }


    @Transactional
    public void delete(Long id) {
        if (!trainningRepository.existsById(id)) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
        try {
            trainningRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
    }


    private String generatePassword(int NumberOfCharacters) {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(NumberOfCharacters);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(NumberOfCharacters);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(NumberOfCharacters);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        return gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
    }

}
