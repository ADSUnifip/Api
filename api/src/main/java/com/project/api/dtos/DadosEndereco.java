package com.project.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(

        @NotBlank
        String rua,
        @NotBlank
        String bairro,

        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato xxxxx-xxx")
        String cep,
        @NotBlank
        String numero,
        @NotBlank
        String cidade,
        @NotBlank
        String estado
) {
}
