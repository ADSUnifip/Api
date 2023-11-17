package com.project.api.dtos.ProcedimentoDto;

import com.project.api.dtos.TipoAmostra.UpdateTipoAmostraDto;
import com.project.api.models.TipoAmostra;

public record UpdateProcedimentoDto(
        String menemonico,
        String nomeProcedimento,
        UpdateTipoAmostraDto amostraPadrao,
        String metodologia
) {

}
