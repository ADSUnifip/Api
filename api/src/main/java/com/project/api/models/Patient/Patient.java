package com.project.api.models.Patient;

import com.project.api.dtos.PatientDto.PatientDto;
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

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String telephone;

    @Column(nullable = false)
    private Boolean active;

    public Patient(PatientDto patientDto) {
        this.fullName = patientDto.getFullName();
        this.cpf = patientDto.getCpf();
        this.birthDate = patientDto.getBirthDate();
        this.sex = patientDto.getSex();
        this.telephone = patientDto.getTelephone();
        this.email = patientDto.getEmail();
        this.active = true;
    }
    public Patient() {
    }
}
