package com.project.api.dtos.TipoAmostra;

import jakarta.validation.constraints.NotBlank;

public record CrateTipoAmostraDto(
        @NotBlank(message = "Código é obrigatorio")
        String codigo,
        @NotBlank(message = "Nome da Amostra é obrigatorio")
        String nomeAmostra,
        @NotBlank(message = "Conservante é obrigatorio")
        String conservante
) {
}
