package com.project.api.filters;

import com.project.api.services.authentication.TokenService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSeguranca extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recuperarToken(request);

        System.out.println("Antes: " + token);
        var subject = tokenService.getSubject(token);

        System.out.println(subject);

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var autorizacaoHeader = request.getHeader("Authorization");
        if (autorizacaoHeader == null) {
            throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization");
        }

        return autorizacaoHeader.replace("Bearer ", "");
    }
}
