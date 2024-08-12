package br.com.connectfy.EurofarmaCliente.repositories;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT u FROM Employee u where u.userName =:userName")
    Optional<Employee> findByUsername(@Param("userName") String userName);

    @Modifying
    @Query("UPDATE Employee p SET p.enabled =:status WHERE p.id = :id")
    void toggleEmployeeStatus(@Param("id") Long id, @Param("status") boolean status);

    @Modifying
    @Query("UPDATE Employee p SET p.password =:password WHERE p.id =:id")
    void changePassword(@Param("id") Long id, @Param("password") String password);

    @Modifying
    @Query("UPDATE Employee p SET p.cellphoneNumber =:cellphoneNumber WHERE p.id =:id")
    void changeCellphoneNumber(@Param("id") Long id, @Param("cellphoneNumber") String cellphoneNumber);

    boolean existsByCellphoneNumber(@Param("cellphoneNumber") String cellphoneNumber);

    boolean existsByUserName(@Param("userName") String userName);

    @Query("SELECT e FROM Employee e  LEFT JOIN FETCH e.employeeTrainings t LEFT JOIN FETCH t.training tr WHERE e.id = :id ORDER BY tr.closingDate ASC")
    Optional<Employee> findByIdWithTrainingsSortedByClosingDate(@Param("id") Long id);
}
