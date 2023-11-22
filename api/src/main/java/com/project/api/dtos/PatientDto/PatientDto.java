package com.project.api.dtos.PatientDto;

import com.project.api.models.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class PatientDto {

    @NotBlank(message = "O nome não pode estar em branco")
    String fullName;

    @NotBlank(message = "O CPF não pode estar em branco")
    @CPF(message = "Digite um CPF válido!")
    String cpf;

    @NotNull(message = "A data não pode estar em branco")
    LocalDate birthDate;

    @NotBlank(message = "Selecione Masculino ou Feminino")
    String sex;

    Date menstuDate;

    @NotBlank(message = "O Telefone não pode estar em branco")
    @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$", message = "O telefone deve estar no formato (XX) XXXXX-XXXX")
    String telephone;

    Boolean active;

    @NotNull(message = "O e-mail não pode estar em branco")
    @Email(message = "Digite um e-mail válido!")
    String email;

    @NotNull@Valid
    Endereco endereco;

}
