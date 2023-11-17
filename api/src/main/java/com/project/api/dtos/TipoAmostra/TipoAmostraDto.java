package com.project.api.dtos.TipoAmostra;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record TipoAmostraDto(
        UUID id,
        String codigo,
        String nomeAmostra,
        String conservante
) {
}
