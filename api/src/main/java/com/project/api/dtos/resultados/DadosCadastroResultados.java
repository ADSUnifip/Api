package com.project.api.dtos.resultados;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosCadastroResultados(@NotNull UUID idProcedimento) {
}
