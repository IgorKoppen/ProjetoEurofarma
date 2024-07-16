package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.InstructorTrainingsDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainingHistoricDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Instructor;
import br.com.connectfy.EurofarmaCliente.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Transactional
    public ResponseEntity<String> insert(InstructorDTO instructorDTO) {
    Instructor instructor = new Instructor(instructorDTO.employee(),instructorDTO.trainnings());
    instructorRepository.save(instructor);
        return ResponseEntity.ok("Instrutor inserido com sucesso!");
    }
    @Transactional(readOnly = true)
    public InstructorDTO getById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
//
//        List<String> instructorNames = instructor.getTrainnings().stream()
//                .flatMap(training -> training.getInstructors().stream())
//                .map(trainingInstructor -> trainingInstructor.getEmployee().getName()).distinct().collect(Collectors.toList());

        return new InstructorDTO(
                instructor.getId(),
                instructor.getEmployee(),
                instructor.getTrainnings(),
                null
        );
    }
    @Transactional(readOnly = true)
    public List<InstructorDTO> findAll() {
        List<Instructor> instructors = instructorRepository.findAll();

        return instructors.stream()
                .map(instructor -> {
                    List<String> instructorNames = instructor.getTrainnings().stream()
                            .flatMap(training -> training.getInstructors().stream())
                            .map(trainingInstructor -> {
                                Employee employee = trainingInstructor.getEmployee();
                                return employee != null ? employee.getName() : null;
                            })
                            .filter(Objects::nonNull)
                            .distinct()
                            .collect(Collectors.toList());

                    Employee instructorEmployee = instructor.getEmployee();
                    return new InstructorDTO(
                            instructor.getId(),
                            instructorEmployee,
                            instructor.getTrainnings(),
                            instructorNames
                    );
                })
                .collect(Collectors.toList());
    }
    @Transactional
    public ResponseEntity<String> update(Long id, InstructorDTO instructorDTO) {
        Instructor updateInstructor = instructorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        updateInstructor.setEmployee(instructorDTO.employee());
        updateInstructor.setTrainnings(instructorDTO.trainnings());
        instructorRepository.save(updateInstructor);
        return ResponseEntity.ok("Instrutor atualizado com sucesso!");
    }


    @Transactional
    public void delete(Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
        try {
            instructorRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
    }

    @Transactional(readOnly = true)
    public List<TrainingHistoricDTO> findTrainingById(Long id) {

        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));


        if(instructor.getTrainnings().isEmpty()){
            return new ArrayList<>();
        }

        return instructor.getTrainnings().stream()
                .map(training -> {
                    List<String> instructorNames = training.getInstructors().stream()
                            .map(trainingInstructor -> {
                                Employee employee = trainingInstructor.getEmployee();
                                return employee != null ? employee.getName() : "Desconhecido";
                            })
                            .distinct().collect(Collectors.toList());

                    return new TrainingHistoricDTO(
                            training.getId(),
                            training.getName(),
                            training.getCode(),
                            training.getCreationDate(),
                            training.getClosingDate(),
                            training.isStatus(),
                            training.getPassword(),
                            training.getDescription(),
                            instructorNames,
                            training.getTags(),
                            training.getEmployees()
                    );
                })
                .collect(Collectors.toList());
    }

}
