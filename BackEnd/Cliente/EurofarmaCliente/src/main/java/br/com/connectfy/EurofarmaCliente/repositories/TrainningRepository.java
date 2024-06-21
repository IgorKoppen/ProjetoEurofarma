package br.com.connectfy.EurofarmaCliente.repositories;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Trainning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainningRepository extends JpaRepository<Trainning, Long> {

    @Query("SELECT t FROM Trainning t where t.code =:code")
    Trainning findTrainingByCode(@Param("code") String code);


}
