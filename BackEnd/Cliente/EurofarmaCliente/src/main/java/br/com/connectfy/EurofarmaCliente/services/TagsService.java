package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.TagDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Tag;
import br.com.connectfy.EurofarmaCliente.repositories.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagsService {

    @Autowired
    private TagsRepository tagsRepository;

    @Transactional
    public ResponseEntity<String> insert(TagDTO tagDTO) {
        Tag tag = new Tag(tagDTO);
        tagsRepository.save(tag);
        return ResponseEntity.ok("Tag inserido com sucesso!");
    }

    @Transactional(readOnly = true)
    public TagDTO getById(Long id) {
        Tag tag = tagsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return new TagDTO(tag.getId(), tag.getName(), tag.getColor(), tag.getTrainnings());
    }

    @Transactional(readOnly = true)
    public List<TagDTO> findAll() {
        List<Tag> tags = tagsRepository.findAll();
        return tags.stream().map(tag
                        -> new TagDTO(tag.getId(),
                        tag.getName(),tag.getColor(), tag.getTrainnings()))
                .collect(Collectors.toList());
    }
    @Transactional
    public ResponseEntity<String> update(Long id, TagDTO tagDTO) {
        Tag updateTag = tagsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        updateTag.setName(tagDTO.name());
        updateTag.setColor(tagDTO.color());
        tagsRepository.save(updateTag);
        return ResponseEntity.ok("Tag atualizado com sucesso!");
    }

    @Transactional
    public void delete(Long id) {
        if (!tagsRepository.existsById(id)) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
        try {
            tagsRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
    }
}
