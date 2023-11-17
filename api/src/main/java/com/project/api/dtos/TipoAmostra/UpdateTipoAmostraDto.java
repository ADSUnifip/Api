package com.project.api.dtos.TipoAmostra;

public record UpdateTipoAmostraDto(
        String codigo,
        String nomeAmostra,
        String conservante
) {
}
