package com.project.api.models;

import com.project.api.dtos.ProcedimentoDto.UpdateProcedimentoDto;
import com.project.api.dtos.TipoAmostra.CrateTipoAmostraDto;
import com.project.api.dtos.TipoAmostra.UpdateTipoAmostraDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Entity(name = "TipoAmostra")
@Table(name = "tb_amostra")
@Data
@NoArgsConstructor
public class TipoAmostra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nomeAmostra;

    @Column(nullable = false, length = 100)
    private String conservante;

    public TipoAmostra(CrateTipoAmostraDto dto) {
        this.codigo = dto.codigo();
        this.nomeAmostra = dto.nomeAmostra();
        this.conservante = dto.conservante();
    }

    public void edit(UpdateTipoAmostraDto dto) {
        if (dto.codigo() != null) {
            this.codigo = dto.codigo();
        }
        if (dto.nomeAmostra() != null){
            this.nomeAmostra = dto.nomeAmostra();
        }
        if (dto.conservante() != null) {
            this.conservante = dto.conservante();
        }
    }
}
