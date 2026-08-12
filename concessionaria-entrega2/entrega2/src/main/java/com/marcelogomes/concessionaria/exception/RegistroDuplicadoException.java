package com.marcelogomes.concessionaria.exception;

// disparada quando chassi, placa ou CPF já existem no banco
public class RegistroDuplicadoException extends RuntimeException {

    public RegistroDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
