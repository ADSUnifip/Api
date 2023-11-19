package com.project.api.dtos.usuario;

import com.project.api.dtos.DadosAtualizacaoEndereco;
import com.project.api.dtos.DadosEndereco;
import com.project.api.models.usuario.Sexo;
import com.project.api.models.usuario.TipoUsuario;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DadosAtualizacaoUsuario(
        String nome,
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Digite um CPF válido no formato xxx.xxx.xxx-xx")
        String cpf,
        @PastOrPresent(message = "A data deve ser no passado ou no presente")
        LocalDate dataNascimento,
        Sexo sexo,

        @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$", message = "O telefone deve estar no formato (XX) XXXXX-XXXX")
        String telefone,

        DadosAtualizacaoEndereco endereco,
        TipoUsuario tipoUsuario

) {
}
