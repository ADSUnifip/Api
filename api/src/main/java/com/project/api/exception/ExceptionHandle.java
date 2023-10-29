package com.project.api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.FormatterClosedException;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity Error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity Error400(MethodArgumentNotValidException exception){
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ExceptionHandlerDto::new).toList());
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity Error409(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito");
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity Error401(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ação Não Autorizada");
    }
    @ExceptionHandler(FormatterClosedException.class)
    public ResponseEntity Erro403(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sem direito de acesso ao conteúdo solicitado");
    }


}
