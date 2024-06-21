package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.EmployeeDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainningDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainningHistoricDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.EmployeeAlreadyInTrainning;
import br.com.connectfy.EurofarmaCliente.exceptions.PasswordDontMatch;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Trainning;
import br.com.connectfy.EurofarmaCliente.repositories.TrainningRepository;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

@Service
public class TrainningService {

    @Autowired
    private TrainningRepository trainningRepository;

    @Transactional
    public ResponseEntity<String> create(TrainningDTO trainningDTO) {
        LocalDateTime now = LocalDateTime.now();
        Trainning trainning = new Trainning();
        trainning.setName(trainningDTO.name());
        trainning.setCode(generatePassayPassword(2));
        trainning.setDescription(trainningDTO.description());
        trainning.setCreationDate(now);
        trainning.setClosingDate(trainningDTO.closingDate());
        trainning.setPassword(generatePassayPassword(1));
        trainning.setStatus(true);
        trainning.setInstructors(trainningDTO.instructor());
        trainning.setTags(trainningDTO.tags());
        trainningRepository.save(trainning);
        return ResponseEntity.ok("Treinamento inserido com sucesso!");
    }

    @Transactional(readOnly = true)
    public TrainningHistoricDTO getById(Long id) {
        Trainning trainning = trainningRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return new TrainningHistoricDTO(trainning.getId(), trainning.getName(), trainning.getCode(),
                trainning.getCreationDate(),trainning.getClosingDate(),trainning.getStatus(),
                trainning.getPassword(),trainning.getDescription(),trainning.getInstructors(), trainning.getTags(), trainning.getEmployees());
    }
    @Transactional(readOnly = true)
    public List<TrainningHistoricDTO> findAll() {
        List<Trainning> trainnings = trainningRepository.findAll();
        return trainnings.stream().map(trainning
                        ->  new TrainningHistoricDTO(trainning.getId(), trainning.getName(), trainning.getCode(),
                trainning.getCreationDate(),trainning.getClosingDate(),trainning.getStatus(),
                trainning.getPassword(),trainning.getDescription(),trainning.getInstructors(), trainning.getTags(), trainning.getEmployees()))
                .collect(Collectors.toList());
    }
    @Transactional
    public ResponseEntity<String> update(Long id, TrainningDTO trainningDTO) {
        Trainning updateTrainning= trainningRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        updateTrainning.setName(trainningDTO.name());
        updateTrainning.setCode(trainningDTO.code());
        updateTrainning.setDescription(trainningDTO.description());
        updateTrainning.setCreationDate(trainningDTO.creationDate());
        updateTrainning.setClosingDate(trainningDTO.closingDate());
        updateTrainning.setPassword(trainningDTO.password());
        updateTrainning.setStatus(trainningDTO.status());
        updateTrainning.setInstructors(trainningDTO.instructor());
        updateTrainning.setTags(trainningDTO.tags());
        updateTrainning.setEmployees(trainningDTO.employees());
        return ResponseEntity.ok("Treinamento atualizado com sucesso!");
    }

    @Transactional
    public ResponseEntity<?> addEmployee(String code,String password, EmployeeDTO employeeDTO) {
        try {
            Trainning trainning = trainningRepository.findTrainingByCode(code);
            if(!trainning.getPassword().equals(password)) {
                throw new PasswordDontMatch("Senha Incorreta!");
            }
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


    private String generatePassayPassword(int NumberOfCharacters) {
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
