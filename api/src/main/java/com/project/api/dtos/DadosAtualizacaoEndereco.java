package com.project.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoEndereco(
        String rua,

        String bairro,

        String cidade,

        String numero,
        String estado
) {
}
