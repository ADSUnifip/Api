package com.project.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(

        @NotBlank
        String rua,
        @NotBlank
        String bairro,
        @NotBlank
        String numero,
        @NotBlank
        String cidade,
        @NotBlank
        String estado
) {
}
