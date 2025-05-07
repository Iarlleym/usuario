package com.engcode.usuario.infrastructure.exceptions;

public class IllegalArgumentExcepition extends RuntimeException {

    public IllegalArgumentExcepition(String menssagem) {

        super(menssagem);
    }

    public IllegalArgumentExcepition(String menssagem, Throwable throwable) {
        super(menssagem, throwable);
    }

}
