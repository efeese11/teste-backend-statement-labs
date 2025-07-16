package prosefa.dto;


import lombok.*;
import prosefa.enums.EstadoSelo;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SeloDTO {
    private UUID id;
    private String codigo;
    private String produto;
    private LocalDateTime dataEmissao;
    private EstadoSelo estado;
    private UUID empresaId;
}
