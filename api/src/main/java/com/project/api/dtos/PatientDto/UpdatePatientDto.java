package com.project.api.dtos.PatientDto;

import com.project.api.models.Endereco;

import java.util.Date;

public record UpdatePatientDto (
    String fullName,
    String cpf,
    Date birthDate,
    String sex,

    String telephone,
    String email,
    Endereco endereco

){}
