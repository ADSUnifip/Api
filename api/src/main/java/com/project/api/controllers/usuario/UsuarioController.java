package com.project.api.controllers.usuario;

import com.project.api.dtos.usuario.BuscarPorNomeDTO;
import com.project.api.dtos.usuario.DadosAtualizacaoUsuario;
import com.project.api.dtos.usuario.DadosCadastroUsuario;
import com.project.api.dtos.usuario.DadosListagemUsuario;
import com.project.api.models.usuario.Usuario;
import com.project.api.repositories.usuario.UsuarioRepository;
//import com.project.api.services.authentication.TokenService;
import com.project.api.services.usuario.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository repository;


    //private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity criarUsuario(@ModelAttribute @Valid DadosCadastroUsuario dados, HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        var usuario = usuarioService.criarUsuario(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemUsuario>> listarUsuarios() {
        var usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable UUID id) {
        var usuario = usuarioService.buscarPorID(id);
        if (usuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/nome")
    public ResponseEntity buscarPorNome(@RequestBody BuscarPorNomeDTO dados) {
        var usuario = usuarioService.buscarPorNome(dados.nome());
        if (usuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity buscarPorCpf(@PathVariable String cpf) {
        var usuario = usuarioService.buscarPorCpf(cpf);
        if (usuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity atualizarUsuario(@PathVariable UUID id, @RequestBody DadosAtualizacaoUsuario dados) {
        var usuarioAtualizado = usuarioService.atualizarUsuario(id, dados);
        if (usuarioAtualizado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
