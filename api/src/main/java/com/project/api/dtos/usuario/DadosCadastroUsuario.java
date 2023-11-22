package com.project.api.dtos.usuario;

import com.project.api.dtos.DadosEndereco;
import com.project.api.models.usuario.Sexo;
import com.project.api.models.usuario.TipoUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Date;

public record DadosCadastroUsuario(

        @NotBlank(message = "O nome não pode estar em branco")
        String nome,
        @NotBlank(message = "O CPF não pode estar em branco")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Digite um CPF válido no formato xxx.xxx.xxx-xx")
        String cpf,
        @NotNull(message = "A data não pode estar em branco")
        @PastOrPresent(message = "A data deve ser no passado ou no presente")
        LocalDate dataNascimento,
        @NotBlank(message = "O sexo não pode estar em branco")
        @Pattern(regexp = "^[MF]$", message = "O sexo deve ser Masculino ou Feminíno")
        String sexo,

        @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$", message = "O telefone deve estar no formato (XX) XXXXX-XXXX")
        String telefone,

        @NotNull
        @Valid
        DadosEndereco endereco,

        @NotBlank(message = "O e-mail não pode estar em branco")
        @Email(message = "Digite um e-mail válido!")
        String email,
        @NotBlank(message = "A senha não pode estar em branco")
        String senha,
        @NotNull(message = "O tipo de usuário não pode estar em branco")
        TipoUsuario tipoUsuario

) {
}
