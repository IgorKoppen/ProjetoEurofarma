package br.com.connectfy.EurofarmaCliente.repositories;

import br.com.connectfy.EurofarmaCliente.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TagsRepository extends JpaRepository<Tag, Long> {
    @Transactional(readOnly = true)
    Optional<Tag> findByNameIgnoreCase(String name);
}
