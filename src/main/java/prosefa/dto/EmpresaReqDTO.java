package prosefa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import prosefa.enums.TipoEmpresa;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresaReqDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String nif;

    @NotNull
    private TipoEmpresa tipo;


}
