package com.project.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatientDto {

    @NotBlank
    private String fullName;
    @NotBlank
    private String cpf;
    @NotBlank
    private LocalDateTime birthDate;
    @NotBlank
    private String sex;
    @NotBlank
    private LocalDateTime menstuDate;
    @NotBlank
    private String telephone;
    @NotBlank
    private Boolean active;
}
