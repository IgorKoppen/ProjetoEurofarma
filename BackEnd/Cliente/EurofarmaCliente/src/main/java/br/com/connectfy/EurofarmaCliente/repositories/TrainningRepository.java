package br.com.connectfy.EurofarmaCliente.repositories;


import br.com.connectfy.EurofarmaCliente.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainningRepository extends JpaRepository<Training, Long> {

    @Query("SELECT t FROM Training t where t.code =:code")
    Training findTrainingByCode(@Param("code") String code);

}
