package br.com.connectfy.EurofarmaCliente.repositories;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeRepository, Long> {

    @Query("SELECT u FROM User u where u.userName =: userName")
    Employee findByUsername(@Param("userName") String username);

    @Modifying
    @Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id")
    void disablePerson(@Param("id") Long id);
}
