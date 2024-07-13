package br.com.connectfy.EurofarmaCliente.repositories;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface TrainningRepository extends JpaRepository<Training, Long> {

    @Query("SELECT t FROM Training t where t.code =:code")
    Training findTrainingByCode(@Param("code") String code);

    @Modifying()
    @Query("UPDATE Training t SET t.employees = :employee WHERE t.id = :id")
    void addEmployee(@Param("employee") List<Employee> employee, @Param("id") Long id);
}
