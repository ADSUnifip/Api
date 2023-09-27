package com.project.api.models.Patient;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;


import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@Table(name = "TB_PATIENT")
public class Patient implements Serializable {
    private static final long serialVersonUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 256)
    private String fullName;

    @Column(nullable = false, length = 50)
    private String cpf;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false, length = 50)
    private String sex;

    @Column(nullable = false)
    private Date menstuDate;

    @Column(nullable = false, length = 50)
    private String telephone;

    @Column(nullable = false)
    private Boolean active;

    public Patient(UUID id, String fullName, String cpf, Date birthDate, String sex, Date menstuDate, String telephone, Boolean active) {
        this.id = id;
        this.fullName = fullName;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.sex = sex;
        this.menstuDate = menstuDate;
        this.telephone = telephone;
        this.active = active;
    }
    public Patient() {
    }
}
