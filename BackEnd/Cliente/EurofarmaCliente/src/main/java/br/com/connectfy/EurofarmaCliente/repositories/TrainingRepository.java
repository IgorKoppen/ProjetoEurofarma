package br.com.connectfy.EurofarmaCliente.repositories;
import br.com.connectfy.EurofarmaCliente.models.EmployeeTraining;
import br.com.connectfy.EurofarmaCliente.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long>, JpaSpecificationExecutor<Training> {

    @Transactional(readOnly = true)
    @Query("SELECT t FROM Training t where t.code =:code")
    Optional<Training> findTrainingByCode(@Param("code") String code);
    @Transactional(readOnly = true)
    @Query("SELECT et FROM EmployeeTraining et WHERE et.employee.id = :id ORDER BY et.registrationDate DESC")
    Optional<List<EmployeeTraining>> findEmployeeTrainingSortedByRegistrationDateById(@Param("id") Long id);
    @Transactional(readOnly = true)
    @Query("SELECT t FROM Training t JOIN t.instructors et WHERE et.id = :id ORDER BY t.creationDate ASC")
    Optional<List<Training>> findByIdInstructorTrainingsSortedByCreationDate(@Param("id") Long id);
    @Transactional(readOnly = true)
    @Query("SELECT t FROM Training t " +
            "JOIN t.instructors et " +
            "JOIN t.tags tag " +
            "WHERE et.id = :id AND LOWER(tag.name) = LOWER(:tagName) " +
            "ORDER BY t.creationDate ASC")
    Optional<List<Training>> findByIdInstructorTrainingsByTagSortedByCreationDate(@Param("id") Long id, @Param("tagName") String tagName);

    @Transactional
    boolean existsByCode(String code);
}
