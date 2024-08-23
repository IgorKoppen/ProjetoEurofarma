package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorIdAndFullNameAndEmployeeRegistrationDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Instructor;
import br.com.connectfy.EurofarmaCliente.repositories.InstructorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum instrutor encontrado com id: " + id));


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
    public Page<InstructorDTO> findWithPagination(Pageable pageable) {
        Page<Instructor> instructorPage = instructorRepository.findAll(pageable);
        return instructorPage.map(InstructorDTO::new);
    }

    @Transactional(readOnly = true)
    public List<InstructorIdAndFullNameAndEmployeeRegistrationDTO> findAllIdAndFullNameAndRegistration() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<InstructorIdAndFullNameAndEmployeeRegistrationDTO> instructorIdAndFullNameDTOS = new ArrayList<>();
        for (Instructor instructor : instructors) {
            instructorIdAndFullNameDTOS.add(new InstructorIdAndFullNameAndEmployeeRegistrationDTO(instructor.getId(), instructor.getEmployee().getName(), instructor.getEmployee().getSurname(), (instructor.getEmployee().getName() + " " + instructor.getEmployee().getSurname() + ". RE: " + instructor.getEmployee().getEmployeeRegistration()), instructor.getEmployee().getEmployeeRegistration()));
        }
        return instructorIdAndFullNameDTOS;
    }


}
