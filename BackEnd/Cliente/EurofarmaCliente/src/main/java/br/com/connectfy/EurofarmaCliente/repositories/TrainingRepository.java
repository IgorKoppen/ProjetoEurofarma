package br.com.connectfy.EurofarmaCliente.repositories;

import br.com.connectfy.EurofarmaCliente.models.EmployeeTraining;
import br.com.connectfy.EurofarmaCliente.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("SELECT t FROM Training t where t.code =:code")
    Optional<Training> findTrainingByCode(@Param("code") String code);

    @Query("SELECT et FROM EmployeeTraining et WHERE et.employee.id = :id ORDER BY et.registrationDate DESC")
    Optional<List<EmployeeTraining>> findEmployeeTrainingSortedByRegistrationDateById(@Param("id") Long id);

    @Query("SELECT t FROM Training t JOIN t.instructors et WHERE et.id = :id ORDER BY t.creationDate ASC")
    Optional<List<Training>> findByIdInstructorTrainingsSortedByCreationDate(@Param("id") Long id);

    boolean existsByCode(String code);
}
