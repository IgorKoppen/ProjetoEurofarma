package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagInfoDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.AlreadyExisteException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Tag;
import br.com.connectfy.EurofarmaCliente.repositories.TagsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagsRepository tagsRepository;

    public TagService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    @Transactional
    public TagInfoDTO insert(TagDTO tagDTO) {
        Optional<Tag> existingTag = tagsRepository.findByNameIgnoreCase(tagDTO.name());
        if (existingTag.isPresent()) {
           throw new AlreadyExisteException("Tag " + tagDTO.name() + " já existe!");
        }
        Tag tag = new Tag(tagDTO);
        return toDto(tagsRepository.save(tag));
    }

    @Transactional(readOnly = true)
    public TagInfoDTO getById(Long id) {
        Tag tag = tagsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return new TagInfoDTO(tag);
    }

    @Transactional(readOnly = true)
    public List<TagInfoDTO> findAll() {
        List<Tag> tags = tagsRepository.findAll();
        return tags.stream().map(TagInfoDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public TagInfoDTO update(Long id, TagDTO tagDTO) {
        Tag updateTag = tagsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        updateTag.setName(tagDTO.name());
        updateTag.setColor(tagDTO.color());

        return toDto(tagsRepository.save(updateTag));
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
    private TagInfoDTO toDto(Tag etity) {
        return new TagInfoDTO(etity);
    }

}
