package com.project.api.dtos.ProcedimentoDto;

import com.project.api.models.Procedimento;
import com.project.api.models.TipoAmostra;

import java.util.UUID;

public record ResponsePorcedimentoDto(
        UUID id,
        String menemonico,
        String nomeProcedimento,
        TipoAmostra amostraPadrao,
        String metodologia
) {



    public ResponsePorcedimentoDto(Procedimento procedimento) {
        this(procedimento.getId(), procedimento.getMenemonico(), procedimento.getNomeProcedimento(), procedimento.getAmostraPadrao(), procedimento.getMetodologia());
    }
}
