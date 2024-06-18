package br.com.connectfy.EurofarmaCliente.repositories;

import br.com.connectfy.EurofarmaCliente.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
