package com.project.api.services.usuario;

import com.project.api.dtos.usuario.DadosAtualizacaoUsuario;
import com.project.api.dtos.usuario.DadosCadastroUsuario;
import com.project.api.dtos.usuario.DadosListagemUsuario;
import com.project.api.models.usuario.Usuario;
import com.project.api.repositories.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<DadosListagemUsuario> listarUsuarios() {
        var usuarios = usuarioRepository.findAll();
        List<DadosListagemUsuario> dadosUsuarios = usuarios.stream()
                .map(usuario -> new DadosListagemUsuario(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getCpf(),
                        usuario.getDataNacimento(),
                        usuario.getSexo(),
                        usuario.getEmail(),
                        usuario.getTipoUsuario()
                ))
                .collect(Collectors.toList());
        return dadosUsuarios;
    }

    public DadosListagemUsuario buscarPorID(UUID id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            DadosListagemUsuario usuarioReturn = new DadosListagemUsuario(usuario.getId(),
                    usuario.getNome(),
                    usuario.getCpf(),
                    usuario.getDataNacimento(),
                    usuario.getSexo(),
                    usuario.getEmail(),
                    usuario.getTipoUsuario());
            return usuarioReturn;
        }
        return null;
    }

    public DadosListagemUsuario buscarPorNome(String nome) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNome(nome);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            DadosListagemUsuario usuarioReturn = new DadosListagemUsuario(usuario.getId(),
                    usuario.getNome(),
                    usuario.getCpf(),
                    usuario.getDataNacimento(),
                    usuario.getSexo(),
                    usuario.getEmail(),
                    usuario.getTipoUsuario());
            return usuarioReturn;
        }
        return null;
    }

    public DadosListagemUsuario buscarPorEmail(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.buscarPorEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            DadosListagemUsuario usuarioReturn = new DadosListagemUsuario(usuario.getId(),
                    usuario.getNome(),
                    usuario.getCpf(),
                    usuario.getDataNacimento(),
                    usuario.getSexo(),
                    usuario.getEmail(),
                    usuario.getTipoUsuario());
            return usuarioReturn;
        }
        return null;
    }

    public Optional<Usuario> buscarPorEmailAuth(String email) {
        return usuarioRepository.buscarPorEmail(email);
    }

    public DadosListagemUsuario buscarPorCpf(String cpf) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCpf(cpf);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            DadosListagemUsuario usuarioReturn = new DadosListagemUsuario(usuario.getId(),
                    usuario.getNome(),
                    usuario.getCpf(),
                    usuario.getDataNacimento(),
                    usuario.getSexo(),
                    usuario.getEmail(),
                    usuario.getTipoUsuario());
            return usuarioReturn;
        }
        return null;
    }

    public DadosListagemUsuario atualizarUsuario(UUID id, DadosAtualizacaoUsuario dados) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (dados.nome() != null) {
                usuario.setNome(dados.nome());
            }

            if (dados.cpf() != null) {
                usuario.setCpf(dados.cpf());
            }

            if (dados.dataNascimento() != null) {
                usuario.setDataNacimento(dados.dataNascimento());
            }

            if (dados.sexo() != null) {
                usuario.setSexo(dados.sexo());
            }

            if (dados.tipoUsuario() != null) {
                usuario.setTipoUsuario(dados.tipoUsuario());
            }

            usuarioRepository.save(usuario);
            DadosListagemUsuario usuarioReturn = new DadosListagemUsuario(usuario.getId(),
                    usuario.getNome(),
                    usuario.getCpf(),
                    usuario.getDataNacimento(),
                    usuario.getSexo(),
                    usuario.getEmail(),
                    usuario.getTipoUsuario());
            return usuarioReturn;
        }
        return null;
    }
}
