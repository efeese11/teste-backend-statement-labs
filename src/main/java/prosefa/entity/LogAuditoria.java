package prosefa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "logs_auditoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogAuditoria {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String entidade;

    @Column(nullable = false)
    private String acao;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(columnDefinition = "TEXT")
    private String detalhes;
}
