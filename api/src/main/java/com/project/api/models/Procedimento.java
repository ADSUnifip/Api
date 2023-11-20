package com.project.api.models;

import com.project.api.dtos.ProcedimentoDto.CreateProcedimentoDto;
import com.project.api.dtos.ProcedimentoDto.UpdateProcedimentoDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity()
@Table(name = "tb_procedimento")
@Data()
@NoArgsConstructor
public class Procedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 155)
    private String menemonico;

    @Column(nullable = false, length = 200)
    private String nomeProcedimento;

    @Column(nullable = false, length = 255)
    private String amostraPadrao;

    @Column(nullable = false, length = 200)
    private String metodologia;

    private boolean ativo;

    public Procedimento(CreateProcedimentoDto dto) {
        this.menemonico = dto.menemonico();
        this.nomeProcedimento = dto.nomeProcedimento();
        this.amostraPadrao = dto.amostraPadrao();
        this.metodologia = dto.metodologia();
        this.ativo = true;
    }

    public void delete(){
        this.ativo = false;
    }
    public void edit(UpdateProcedimentoDto dto) {
        if (dto.menemonico() != null){
            this.menemonico = dto.menemonico();
        }
        if (dto.nomeProcedimento() != null) {
            this.nomeProcedimento = dto.nomeProcedimento();
        }
        if (dto.amostraPadrao() != null) {
            this.amostraPadrao = dto.amostraPadrao();
        }
        if (dto.metodologia() != null) {
            this.metodologia = dto.metodologia();
        }
    }
}
