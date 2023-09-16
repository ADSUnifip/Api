package com.project.api.models.medicoAssinante;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Data
@Table(name = "TB_MEDICO_ASSINANTE")
public class MedicoAssinante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String numeroConselho;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UFConselho ufConselho;

    @Column(nullable = false)
    private String tipoConselho;

    @Column(nullable = false)
    private String nomeMedicoAssinante;

    @Column(nullable = false)
    private boolean isActive;

    public MedicoAssinante(String numeroConselho, UFConselho ufConselho, String tipoConselho, String nomeMedicoAssinante) {
        this.numeroConselho = numeroConselho;
        this.ufConselho = ufConselho;
        this.tipoConselho = tipoConselho;
        this.nomeMedicoAssinante = nomeMedicoAssinante;
        this.isActive = true;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
