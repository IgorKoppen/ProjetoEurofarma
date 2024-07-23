package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.InstructorNameAndIdDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainingHistoricDTO;
import br.com.connectfy.EurofarmaCliente.services.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eurofarma/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping
    public ResponseEntity<String> insertInstructor(@RequestBody @Valid InstructorDTO instructorDTO) {
        return instructorService.insert(instructorDTO);
    }
    @GetMapping
    public List<InstructorNameAndIdDTO> findAllInstructors(){
        return instructorService.findAll();
    }

    @GetMapping("/{id}")
    public InstructorDTO findInstructorById(@PathVariable Long id) {
        return instructorService.getById(id);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<String> updateInstructor(@PathVariable Long id,@RequestBody @Valid InstructorDTO instructorDTO) {
        return instructorService.update(id, instructorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteInstructor(@PathVariable Long id) {
        instructorService.delete(id);
    }

    @GetMapping("/findTrainingByInstructor/{id}")
    public List<TrainingHistoricDTO> findInstructorTrainings(@PathVariable Long id) { return instructorService.findTrainingById(id); }

    @GetMapping("/findTrainingByInstructorAndTag/{id}")
    public List<TrainingHistoricDTO> findInstructorTrainings(@PathVariable Long id, @RequestParam String tag) { return instructorService.findTrainingByIdAndTag(id, tag); }
}
