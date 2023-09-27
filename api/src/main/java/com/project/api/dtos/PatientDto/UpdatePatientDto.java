package com.project.api.dtos.PatientDto;

import java.util.Date;

public record UpdatePatientDto (
    String fullName,
    String cpf,
    Date birthDate,
    String sex,
    Date menstuDate,
    String telephone,
    Boolean active
){}
