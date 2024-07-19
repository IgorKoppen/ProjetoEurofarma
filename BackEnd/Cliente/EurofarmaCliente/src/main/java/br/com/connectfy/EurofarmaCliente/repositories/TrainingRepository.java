package br.com.connectfy.EurofarmaCliente.repositories;


import br.com.connectfy.EurofarmaCliente.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("SELECT t FROM Training t where t.code =:code")
    Optional<Training> findTrainingByCode(@Param("code") String code);

    boolean existsByCode(String code);
}
