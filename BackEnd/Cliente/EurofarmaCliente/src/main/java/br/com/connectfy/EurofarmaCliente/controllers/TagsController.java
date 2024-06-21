package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.TagDTO;
import br.com.connectfy.EurofarmaCliente.services.TagsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eurofarma/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @PostMapping
    public ResponseEntity<String> insertTag(@RequestBody @Valid  TagDTO tagDTO) {
        return tagsService.insert(tagDTO);
    }

    @GetMapping
    public List<TagDTO> findAllTags(){
        return tagsService.findAll();
    }

    @GetMapping(value = "/{id}")
    public TagDTO findTagById(@PathVariable Long id) {
        return tagsService.getById(id);
    }

    @PutMapping(value ="/{id}")
    public  ResponseEntity<String> updateTag(@PathVariable Long id,@RequestBody @Valid TagDTO tagDTO) {
        return tagsService.update(id, tagDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagsService.delete(id);
    }
}
