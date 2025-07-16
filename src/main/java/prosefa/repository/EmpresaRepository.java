package prosefa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prosefa.entity.Empresa;

import java.rmi.server.UID;
import java.util.Optional;
import java.util.UUID;

public interface EmpresaRepository extends JpaRepository <Empresa, UID> {
    Optional<Empresa> findByNif(String nif);

    boolean existsByNif(String nif);
    Optional<Empresa> findById(UUID id);

}