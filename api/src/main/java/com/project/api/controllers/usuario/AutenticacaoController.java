package com.project.api.controllers.usuario;

import com.project.api.dtos.usuario.DadosAutenticacao;
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

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        System.out.println("To no inicio");
        var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        System.out.println("To no meio");
        System.out.println(token);
        //var autenticacaio = manager.authenticate(token);
        System.out.println("To no fim");
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
