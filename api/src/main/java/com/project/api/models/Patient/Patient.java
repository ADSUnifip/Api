package com.project.api.models.Patient;


import com.project.api.dtos.PatientDto.PatientDto;
import com.project.api.models.Endereco;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private Boolean active;

    public Patient(PatientDto patientDto) {
        this.fullName = patientDto.getFullName();
        this.cpf = patientDto.getCpf();
        this.birthDate = patientDto.getBirthDate();
        this.sex = patientDto.getSex();
        this.telephone = patientDto.getTelephone();
        this.email = patientDto.getEmail();
        this.endereco = patientDto.getEndereco();
        this.active = true;
    }

    public Patient() {
    }

    public void setBirthDate(Date date) {
    }

}
