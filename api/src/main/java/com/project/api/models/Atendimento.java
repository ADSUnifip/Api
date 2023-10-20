package com.project.api.models;

import com.project.api.dtos.ProcedimentoDto.UpdateProcedimentoDto;
import com.project.api.models.Patient.Patient;
import com.project.api.models.medicoAssinante.MedicoAssinante;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@Entity()
@Table(name = "tb_atendimento")
@Data()
@NoArgsConstructor
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Date date;

    private Boolean active = true;


    @OneToOne
    @JoinColumn(name = "procedimento_id")
    private Procedimento procedimento;

    @OneToOne
    @JoinColumn(name = "medicoAssinante_id")
    private MedicoAssinante medicoAssinante;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Patient paciente;

    public void delete(){
        this.active = false;
    }

}


