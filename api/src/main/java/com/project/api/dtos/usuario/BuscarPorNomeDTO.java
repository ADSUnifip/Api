package com.project.api.dtos.usuario;

import jakarta.validation.constraints.NotBlank;

public record BuscarPorNomeDTO(
        @NotBlank(message = "Você deve informar um nome!")
        String nome
) {
}
