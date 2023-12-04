package com.project.api.services.usuario;

import com.google.gson.Gson;
import com.project.api.dtos.usuario.DadosAtualizacaoUsuario;
import com.project.api.dtos.usuario.DadosCadastroUsuario;
import com.project.api.dtos.usuario.DadosListagemUsuario;
import com.project.api.dtos.usuario.ErrorResponse;
import com.project.api.models.Endereco;
import com.project.api.models.usuario.Usuario;
import com.project.api.repositories.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    //private PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<Object> criarUsuario(DadosCadastroUsuario dados) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCpf(dados.cpf());

        if (usuarioExistente.isPresent()) {
            var error = new ErrorResponse(400, "CPF já cadastrado");

            // Lança uma exceção com a mensagem de erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        Optional<Usuario> usuarioExistenteEmail = usuarioRepository.buscarPorEmail(dados.email());

        if (usuarioExistenteEmail.isPresent()) {
            var error = new ErrorResponse(400, "Email já cadastrado");

            // Lança uma exceção com a mensagem de erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        //var senhaEncriptada = passwordEncoder.encode(dados.senha());
        var senhaEncriptada = dados.senha();
        var usuario = new Usuario(dados.nome(), dados.cpf(), dados.dataNascimento(), dados.sexo(), dados.telefone(), dados.endereco(), dados.email(), dados.senha(), dados.tipoUsuario());
        usuario.setSenha(senhaEncriptada);
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
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
                        usuario.getTelefone(),
                        usuario.getEndereco(),
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
                    usuario.getEndereco(),
                    usuario.getTelefone(),
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
                    usuario.getTelefone(),
                    usuario.getEndereco(),
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
                    usuario.getTelefone(),
                    usuario.getEndereco(),
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
                    usuario.getTelefone(),
                    usuario.getEndereco(),
                    usuario.getEmail(),
                    usuario.getTipoUsuario());
            return usuarioReturn;
        }
        return null;
    }

    public ResponseEntity<Object> atualizarUsuario(UUID id, DadosAtualizacaoUsuario dados) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCpf(dados.cpf());

        if (usuarioExistente.isPresent()) {
            var error = new ErrorResponse(400, "CPF já cadastrado");

            // Lança uma exceção com a mensagem de erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

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

            if (dados.telefone() != null) {
                usuario.setTelefone(dados.telefone());
            }

            if (dados.endereco() != null) {
                usuario.atualizarEndereco(dados.endereco());
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
                    usuario.getTelefone(),
                    usuario.getEndereco(),
                    usuario.getEmail(),
                    usuario.getTipoUsuario());
            return ResponseEntity.status(HttpStatus.OK).body(usuarioReturn);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
