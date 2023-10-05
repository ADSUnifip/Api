package com.project.api.models.medicoSolicitante;



import jakarta.persistence.*;


import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_MEDICO_SOLICITANTE")
public class MedicoSolicitante implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //Atributos medico solicitante
    //crm = numero do conselho
    //uf_crm = estado do conselho
    //nome_compl = nome completo do medico

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String crm;
    @Column(nullable = false)
    private String ufCrm;

    @Column(nullable = false)
    private String nomeCompl;
    @Column(nullable = false)
    private Boolean active;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getUfCrm() {
        return ufCrm;
    }

    public void setUfCrm(String ufCrm) {
        this.ufCrm = ufCrm;
    }

    public String getNomeCompl() {
        return nomeCompl;
    }

    public void setNomeCompl(String nomeCompl) {
        this.nomeCompl = nomeCompl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
