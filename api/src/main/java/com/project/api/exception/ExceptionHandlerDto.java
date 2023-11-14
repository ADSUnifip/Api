package com.project.api.exception;

import org.springframework.validation.FieldError;

public record ExceptionHandlerDto (
    String campo,
    String mensagem
) {
    public ExceptionHandlerDto(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
        }
