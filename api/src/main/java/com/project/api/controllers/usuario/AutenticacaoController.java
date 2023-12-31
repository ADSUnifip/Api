package com.project.api.controllers.usuario;

import com.project.api.dtos.usuario.DadosAutenticacao;
import com.project.api.dtos.usuario.DadosTokenJWT;
import com.project.api.models.usuario.Usuario;
import com.project.api.services.authentication.TokenService;
import com.project.api.services.usuario.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity efetuarLogin(@ModelAttribute @Valid DadosAutenticacao dados, HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var autenticacao = manager.authenticate(token);
        var user = usuarioService.buscarPorEmailAuth((String) token.getPrincipal());
        var tokenJWT = tokenService.gerarToken(user.get());

        return ResponseEntity.status(HttpStatus.OK).body(new DadosTokenJWT(tokenJWT));

    }
}
