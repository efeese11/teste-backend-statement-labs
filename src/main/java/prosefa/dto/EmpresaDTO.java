package prosefa.dto;



import lombok.*;
import prosefa.enums.StatusEmpresa;
import prosefa.enums.TipoEmpresa;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresaDTO {
    private UUID id;
    private String nome;
    private String nif;
    private TipoEmpresa tipo;
    private StatusEmpresa status;
    private LocalDateTime dataRegistro;
}
