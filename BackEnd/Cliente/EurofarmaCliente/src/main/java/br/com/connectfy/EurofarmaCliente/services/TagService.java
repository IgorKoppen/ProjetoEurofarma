package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.AlreadyExistException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Instructor;
import br.com.connectfy.EurofarmaCliente.models.Tag;
import br.com.connectfy.EurofarmaCliente.repositories.TagsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public TagDTO insert(TagDTO tagDTO) {
        Optional<Tag> existingTag = tagsRepository.findByNameIgnoreCase(tagDTO.getName());
        if (existingTag.isPresent()) {
           throw new AlreadyExistException("Tag " + tagDTO.getName() + " já existe!");
        }
        Tag tag = new Tag(tagDTO);
        return toDTO(tagsRepository.save(tag));
    }

    @Transactional(readOnly = true)
    public TagDTO getById(Long id) {
        Tag tag = tagsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return new TagDTO(tag);
    }

    @Transactional(readOnly = true)
    public List<TagDTO> findAll() {
        List<Tag> tags = tagsRepository.findAll();
        return tags.stream().map(TagDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Page<TagDTO> findWithPagination(Pageable pageable) {
        Page<Tag> tagPage = tagsRepository.findAll(pageable);
        return tagPage.map(TagDTO::new);
    }

    @Transactional
    public TagDTO update(Long id, TagDTO tagDTO) {
        Tag updateTag = tagsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        updateTag.setName(tagDTO.getName());
        updateTag.setColor(tagDTO.getColor());

        return toDTO(tagsRepository.save(updateTag));
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
    private TagDTO toDTO(Tag etity) {
        return new TagDTO(etity);
    }

}
