package com.project.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoEndereco(
        String rua,

        String bairro,
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato xxxxx-xxx")
        String cep,
        String cidade,

        String numero,
        String estado
) {
}
