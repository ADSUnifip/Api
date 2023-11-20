package com.project.api.dtos.ProcedimentoDto;

public record UpdateProcedimentoDto(
        String menemonico,
        String nomeProcedimento,
        String amostraPadrao,
        String metodologia
) {

}
