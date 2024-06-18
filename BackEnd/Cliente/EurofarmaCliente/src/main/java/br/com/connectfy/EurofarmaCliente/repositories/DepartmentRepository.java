package br.com.connectfy.EurofarmaCliente.repositories;

import br.com.connectfy.EurofarmaCliente.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
