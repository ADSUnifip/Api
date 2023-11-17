package com.project.api.dtos.medicoAssinante;

import com.project.api.models.medicoAssinante.UFConselho;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMediccoAssinante(
        @NotBlank
        String numeroConselho,

        @NotNull
        UFConselho ufConselho,

        @NotBlank
        String tipoConselho,

        @NotBlank
        String nomeMedicoAssinante


) {
}
