package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagInfoDTO;
import br.com.connectfy.EurofarmaCliente.services.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/eurofarma/tag")
public class TagController {

    @Autowired
    private TagService tagsService;

    @PostMapping
    public ResponseEntity<TagInfoDTO> insert(@RequestBody @Valid TagDTO tagDTO) {
        TagInfoDTO tagInfoDTO = tagsService.insert(tagDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tagInfoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(tagInfoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TagInfoDTO>> findAll(){
        List<TagInfoDTO> TagInfoDTOs = tagsService.findAll();
        return ResponseEntity.ok(TagInfoDTOs);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TagInfoDTO> findById(@PathVariable Long id) {
        TagInfoDTO tagInfoDTO = tagsService.getById(id);
        return ResponseEntity.ok(tagInfoDTO);
    }

    @PutMapping(value ="/{id}")
    public  ResponseEntity<TagInfoDTO> update(@PathVariable Long id,@RequestBody @Valid TagDTO tagDTO) {
        TagInfoDTO tagInfoDTO = tagsService.update(id, tagDTO);
        return ResponseEntity.ok(tagInfoDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tagsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
