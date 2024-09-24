package br.com.connectfy.EurofarmaCliente.repositories;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    @Query("SELECT u FROM Employee u where u.employeeRegistration =:employeeRegistration")
    Optional<Employee> findByEmployeeRegistration(@Param("employeeRegistration") Long employeeRegistration);

    @Modifying
    @Query("UPDATE Employee p SET p.enabled =:status WHERE p.id = :id")
    void toggleEmployeeStatus(@Param("id") Long id, @Param("status") boolean status);

    @Modifying
    @Query("UPDATE Employee p SET p.password =:password WHERE p.id =:id")
    void changePassword(@Param("id") Long id, @Param("password") String password);

    @Modifying
    @Query("UPDATE Employee p SET p.cellphoneNumber =:cellphoneNumber WHERE p.id =:id")
    void changeCellphoneNumber(@Param("id") Long id, @Param("cellphoneNumber") String cellphoneNumber);

    @Modifying
    @Query("UPDATE Employee e SET e.instructor = :instructor WHERE e.id = :employeeId")
    void updateInstructor(@Param("employeeId") Long employeeId, @Param("instructor") Instructor instructor);

    boolean existsByCellphoneNumber(@Param("cellphoneNumber") String cellphoneNumber);

    boolean existsByEmployeeRegistration(@Param("employeeRegistration") Long employeeRegistration);

}
