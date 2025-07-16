package prosefa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prosefa.entity.LogAuditoria;

import java.util.UUID;

public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, UUID>  {
}
