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

        @NotNull
        private UUID empresaId;

        @NotBlank
        private String produto;
}
