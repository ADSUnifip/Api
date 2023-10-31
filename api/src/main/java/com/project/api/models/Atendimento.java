package com.project.api.models;

import com.project.api.dtos.ProcedimentoDto.UpdateProcedimentoDto;
import com.project.api.models.Patient.Patient;
import com.project.api.models.medicoAssinante.MedicoAssinante;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Entity()
@Table(name = "tb_atendimento")
@Data()
@AllArgsConstructor()
@NoArgsConstructor()
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Date date;

    private Boolean active = true;


    @OneToMany()
    @JoinColumn(name = "procedimento_id")
    private List<Procedimento> procedimento;

    @OneToOne
    @JoinColumn(name = "medicoAssinante_id")
    private MedicoAssinante medicoAssinante;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Patient paciente;

    public void delete(){
        this.active = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Procedimento> getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(List<Procedimento> procedimento) {
        this.procedimento = procedimento;
    }

    public MedicoAssinante getMedicoAssinante() {
        return medicoAssinante;
    }

    public void setMedicoAssinante(MedicoAssinante medicoAssinante) {
        this.medicoAssinante = medicoAssinante;
    }

    public Patient getPaciente() {
        return paciente;
    }

    public void setPaciente(Patient paciente) {
        this.paciente = paciente;
    }
}