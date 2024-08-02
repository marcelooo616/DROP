package io.drop.drop_api.exeption;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(){
        super("Senha invalida");
    }
}

