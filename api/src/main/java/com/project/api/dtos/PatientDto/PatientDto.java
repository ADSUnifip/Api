package com.project.api.dtos.PatientDto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.Date;

@Data
public class PatientDto {

    @NotBlank
    private String fullName;

    @NotBlank
    private String cpf;

    @NotNull
    private Date birthDate;

    @NotBlank
    private String sex;

    private Date menstuDate;

    @NotBlank
    private String telephone;

    private Boolean active;
}
