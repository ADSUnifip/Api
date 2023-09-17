package com.project.api.dtos.medicoAssinante;

import com.project.api.models.medicoAssinante.UFConselho;

public record DadosAtualizacaoMedicoAssinante(
        String numeroConselho,
        UFConselho ufConselho,
        String tipoConselho,
        String nomeMedicoAssinante
) {
}
