package com.project.api.dtos.ProcedimentoDto;

import com.project.api.dtos.TipoAmostra.CrateTipoAmostraDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProcedimentoDto(
        @NotBlank(message = "Menemônico é obrigatório")
        String menemonico,
        @NotBlank(message = "Nome do Procedimento é obrigatório")
        String nomeProcedimento,
        @NotNull
        @Valid
        CrateTipoAmostraDto amostraPadrao,
        @NotBlank(message = "Metodologia é obrigatória")
        String metodologia


) {
}
