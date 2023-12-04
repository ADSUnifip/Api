package com.project.api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tb_atendimento_proce_ofc")
@Data
public class AtendimentoProcedi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID atendimento;
    private UUID procedimento;
}
