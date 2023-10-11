package com.project.api.filters;

import com.project.api.repositories.usuario.UsuarioRepository;
import com.project.api.services.authentication.TokenService;
import com.project.api.services.usuario.UsuarioService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSeguranca extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = recuperarToken(request);

        if (token != null) {
            var subject = tokenService.getSubject(token);
            var usuario = usuarioRepository.findByEmail(subject);

            var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var autorizacaoHeader = request.getHeader("Authorization");
        if (autorizacaoHeader != null) {
            return autorizacaoHeader.replace("Bearer ", "");
        }

        return null;
    }
}
