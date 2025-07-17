package prosefa.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class SeloRequestDTO {

    @NotNull(message = "EmpresaId é obrigatório")
    private UUID empresaId;

    @NotBlank(message = "Produto é obrigatório")
    private String produto;

}
