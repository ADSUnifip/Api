package com.project.api.dtos.usuario;

import com.project.api.models.usuario.Sexo;
import com.project.api.models.usuario.TipoUsuario;

import java.time.LocalDate;
import java.util.UUID;

public record DadosListagemUsuario(UUID id, String nome, String cpf, LocalDate dataNascimento, Sexo sexo, String email, TipoUsuario tipoUsuario) {
}
