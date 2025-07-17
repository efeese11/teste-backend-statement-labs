package prosefa.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "NIF é obrigatório")
    private String nif;

    @NotNull(message = "Tipo é obrigatório")
    private TipoEmpresa tipo;

    private StatusEmpresa status;
    private LocalDateTime dataRegistro;
}
