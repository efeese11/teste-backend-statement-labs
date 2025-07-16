package prosefa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prosefa.dto.LogAuditoriaDTO;
import prosefa.entity.LogAuditoria;
import prosefa.repository.LogAuditoriaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogAuditoriaService {

    private final LogAuditoriaRepository repository;

    public void registrar(String entidade, String acao, String usuario, String detalhes) {
        LogAuditoria log = LogAuditoria.builder()
                .entidade(entidade)
                .acao(acao)
                .usuario(usuario)
                .detalhes(detalhes)
                .dataHora(LocalDateTime.now())
                .build();

        repository.save(log);
    }



    public List<LogAuditoriaDTO> listarTodos() {
        return repository.findAll().stream()
                .map(log -> LogAuditoriaDTO.builder()
                        .id(log.getId())
                        .entidade(log.getEntidade())
                        .acao(log.getAcao())
                        .usuario(log.getUsuario())
                        .dataHora(log.getDataHora())
                        .detalhes(log.getDetalhes())
                        .build())
                .toList();
    }

}