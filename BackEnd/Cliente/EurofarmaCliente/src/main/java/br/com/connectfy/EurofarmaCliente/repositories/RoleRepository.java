package br.com.connectfy.EurofarmaCliente.repositories;

import br.com.connectfy.EurofarmaCliente.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
