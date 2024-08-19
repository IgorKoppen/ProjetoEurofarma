package br.com.connectfy.EurofarmaCliente.controllers;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorIdAndFullNameDTO;
import br.com.connectfy.EurofarmaCliente.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

    @GetMapping("/pagination")
    public ResponseEntity<PagedModel<EntityModel<InstructorDTO>>> findWithPagination(
            @PageableDefault(size = 10, direction = Sort.Direction.ASC)
            Pageable pageable, PagedResourcesAssembler<InstructorDTO> assembler) {
        Page<InstructorDTO> instructorDTOPage = instructorService.findWithPagination(pageable);
        PagedModel<EntityModel<InstructorDTO>> pagedModel = assembler.toModel(instructorDTOPage);
        return ResponseEntity.ok(pagedModel);
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



}
