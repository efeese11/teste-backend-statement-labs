package com.teste_backend_statement_labs.teste_backend_statement_labs.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados necessários para registrar a saída de um veículo")
public class CheckOutDTO {
    @NotBlank
    @Schema(description = "Placa do veículo a finalizar", example = "LD-23-45-AB")
    private String plate;

    public CheckOutDTO() {}

    public CheckOutDTO(String plate) {
        this.plate = plate;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
