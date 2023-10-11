package com.project.api.services.usuario;

import com.project.api.dtos.usuario.DadosCadastroUsuario;
import com.project.api.models.usuario.Usuario;
import com.project.api.repositories.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario criarUsuario(DadosCadastroUsuario dados) {
        var senhaEncriptada = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(dados.nome(), dados.cpf(), dados.dataNascimento(), dados.sexo(), dados.email(), dados.senha(), dados.tipoUsuario());
        usuario.setSenha(senhaEncriptada);
        usuarioRepository.save(usuario);
        return  usuario;
    }

    @Transactional
    public List<Usuario> listarUsuarios() {
        var usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Optional<Usuario> buscarPorID(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.buscarPorEmail(email);
    }

    public Optional<Usuario> buscarPorCpf(String cpf) {
        //return usuarioRepository.encontrarPorCpf(cpf);
        return usuarioRepository.findByCpf(cpf);
    }
}
