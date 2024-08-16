package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorIdAndFullNameDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingWithEmployeesInfo;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Instructor;
import br.com.connectfy.EurofarmaCliente.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Transactional(readOnly = true)
    public InstructorDTO findById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));


        return new InstructorDTO(instructor);
    }

    @Transactional(readOnly = true)
    public List<InstructorDTO> findAll() {
        List<Instructor> instructors = instructorRepository.findAll();
        return instructors.stream()
                .map(InstructorDTO::new)
                .collect(Collectors.toList());
    }
@Transactional(readOnly = true)
public List<InstructorIdAndFullNameDTO> findAllIdAndFullName() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<InstructorIdAndFullNameDTO> instructorIdAndFullNameDTOS = new ArrayList<>();
        for (Instructor instructor : instructors) {
            instructorIdAndFullNameDTOS.add(new InstructorIdAndFullNameDTO(instructor.getId(),(instructor.getEmployee().getName() + " " + instructor.getEmployee().getSurname())));
        }
        return instructorIdAndFullNameDTOS;
}


    @Transactional(readOnly = true)
    public List<TrainingWithEmployeesInfo> findTrainingById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        if (instructor.getTrainings().isEmpty()) {
            return new ArrayList<>();
        }
        return instructor.getTrainings().stream()
                .map(TrainingWithEmployeesInfo::new
                ).sorted(Comparator.comparing(TrainingWithEmployeesInfo::getClosingDate).reversed())
                .collect(Collectors.toList());
    }

    private InstructorDTO toDTO(Instructor entity) {
        return new InstructorDTO(entity);
    }
}
