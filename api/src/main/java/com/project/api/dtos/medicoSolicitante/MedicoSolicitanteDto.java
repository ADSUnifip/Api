package com.project.api.dtos.medicoSolicitante;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record MedicoSolicitanteDto(@NotBlank String crm,@NotBlank String ufCrm, @NotBlank String nomeCompl, @NotNull Boolean active) {



}
