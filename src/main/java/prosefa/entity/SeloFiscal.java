package prosefa.entity;

import jakarta.persistence.*;
import lombok.*;
import prosefa.enums.EstadoSelo;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "selos_fiscais", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeloFiscal {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Column(nullable = false)
    private String produto;

    @Column(nullable = false)
    private LocalDateTime dataEmissao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSelo estado;



}
