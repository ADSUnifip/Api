package com.project.api.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(

        @NotBlank(message = "O e-mail não pode estar em branco")
        @Email(message = "Digite um e-mail válido!")
        String email,
        String senha
) {
}
