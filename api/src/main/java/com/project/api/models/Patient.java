package com.project.api.models;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;


import java.io.Serializable;
import java.time.LocalDateTime;
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
    private LocalDateTime birthDate;

    @Column(nullable = false, length = 50)
    private String sex;

    @Column(nullable = false)
    private LocalDateTime menstuDate;

    @Column(nullable = false, length = 50)
    private String telephone;

    @Column(nullable = false)
    private Boolean active;

}
