package prosefa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import prosefa.enums.StatusEmpresa;
import prosefa.enums.TipoEmpresa;

import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "empresas", uniqueConstraints = @UniqueConstraint(columnNames = "nif"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String nif;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEmpresa tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEmpresa status;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;





}
