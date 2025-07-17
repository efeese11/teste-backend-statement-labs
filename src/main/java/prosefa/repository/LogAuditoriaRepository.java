package prosefa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prosefa.entity.LogAuditoria;

import java.util.List;
import java.util.UUID;

public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, UUID>  {

    List<LogAuditoria> findByEntidade(String entidade);

}
