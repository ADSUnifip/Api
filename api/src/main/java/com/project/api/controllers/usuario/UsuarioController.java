package com.project.api.controllers.usuario;

import com.project.api.dtos.usuario.BuscarPorNomeDTO;
import com.project.api.dtos.usuario.DadosCadastroUsuario;
import com.project.api.models.usuario.Usuario;
import com.project.api.services.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/usuario")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity criarUsuario(@RequestBody @Valid DadosCadastroUsuario dados) {
        var usuario = usuarioService.criarUsuario(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        var usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable UUID id) {
        var usuario = usuarioService.buscarPorID(id);
        if (usuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/nome")
    public ResponseEntity buscarPorNome(@RequestBody BuscarPorNomeDTO dados) {
        var usuario = usuarioService.buscarPorNome(dados.nome());
        if (usuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity buscarPorCpf(@PathVariable String cpf) {
        var usuario = usuarioService.buscarPorCpf(cpf);
        if (usuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
