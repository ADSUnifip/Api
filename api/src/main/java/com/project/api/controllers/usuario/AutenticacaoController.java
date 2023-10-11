package com.project.api.controllers.usuario;

import com.project.api.dtos.usuario.DadosAutenticacao;
import com.project.api.dtos.usuario.DadosTokenJWT;
import com.project.api.models.usuario.Usuario;
import com.project.api.services.authentication.TokenService;
import com.project.api.services.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var user = usuarioService.buscarPorEmail((String) token.getPrincipal());
        var tokenJWT = tokenService.gerarToken(user.get());

        return ResponseEntity.status(HttpStatus.OK).body(new DadosTokenJWT(tokenJWT));

        //return ResponseEntity.status(HttpStatus.OK).build();

    }
}
