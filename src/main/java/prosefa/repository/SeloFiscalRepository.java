package prosefa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prosefa.entity.Empresa;
import prosefa.entity.SeloFiscal;
import prosefa.enums.EstadoSelo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SeloFiscalRepository extends JpaRepository<SeloFiscal, UUID> {
    Optional<SeloFiscal> findByCodigo(String codigo);

    long countByEmpresaAndDataEmissaoBeforeAndEstadoNot(
            Empresa empresa, LocalDateTime dataLimite, EstadoSelo estado
    );

    List<SeloFiscal> findByEmpresa(Empresa empresa);

}
