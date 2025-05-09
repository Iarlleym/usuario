package com.engcode.usuario.controller;

import com.engcode.usuario.infrastructure.exceptions.ConflictException;
import com.engcode.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.engcode.usuario.infrastructure.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Anotação que faz que tudo que esteja aki seja aplicado as demais controllers do micro serviço.
@ControllerAdvice
public class GlobalExceptionHandler {

    //Anotação para dizer que é uma exceção global.
    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourceNotFoundException (ResourceNotFoundException resourceNotFoundException) {
        return new ResponseEntity<>(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler (ConflictException.class)
    public ResponseEntity<String> handlerConflictException (ConflictException conflictException) {
        return new ResponseEntity<>(conflictException.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler (UnauthorizedException.class)
    public ResponseEntity<String> handlerUnauthorizedException (UnauthorizedException unauthorizedException) {
        return new ResponseEntity<>(unauthorizedException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler (IllegalArgumentException.class)
    public ResponseEntity<String> handlerIllegalArgumentException (IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
