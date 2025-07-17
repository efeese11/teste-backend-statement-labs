package prosefa.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogAuditoriaDTO {
    private UUID id;
    private String entidade;
    private String acao;
    private String usuario;
    private LocalDateTime dataHora;
    private String detalhes;
}