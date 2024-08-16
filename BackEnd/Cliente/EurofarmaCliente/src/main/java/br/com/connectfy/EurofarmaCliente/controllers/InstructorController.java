package br.com.connectfy.EurofarmaCliente.controllers;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorIdAndFullNameDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingWithEmployeesInfo;
import br.com.connectfy.EurofarmaCliente.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/eurofarma/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;


    @GetMapping
    public ResponseEntity<List<InstructorDTO>> findAll(){
        List<InstructorDTO> list = instructorService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/findAllFullName")
    public ResponseEntity<List<InstructorIdAndFullNameDTO>> findAllFullName() {
        List<InstructorIdAndFullNameDTO> dto  = instructorService.findAllIdAndFullName();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> findById(@PathVariable Long id) {
        InstructorDTO dto  = instructorService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findTrainingByInstructor/{id}")
    public ResponseEntity<List<TrainingWithEmployeesInfo>> findInstructorTrainings(@PathVariable Long id) {
        List<TrainingWithEmployeesInfo> dto = instructorService.findTrainingById(id);
        return ResponseEntity.ok(dto);
    }

}
