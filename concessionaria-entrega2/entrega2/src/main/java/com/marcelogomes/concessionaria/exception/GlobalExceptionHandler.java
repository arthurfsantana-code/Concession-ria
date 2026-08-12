package com.marcelogomes.concessionaria.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // dado inválido no corpo da requisição (@Valid barrou antes de chegar no service)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroValidacaoResponse tratarErroDeValidacao(MethodArgumentNotValidException ex) {
        List<ErroCampo> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(campo -> new ErroCampo(campo.getField(), campo.getDefaultMessage()))
                .toList();
        return new ErroValidacaoResponse(HttpStatus.BAD_REQUEST.value(), erros);
    }

    // id de carro/cliente que não existe no banco
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResponse tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        return new ErroResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    // chassi, placa ou CPF que já existe cadastrado
    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResponse tratarRegistroDuplicado(RegistroDuplicadoException ex) {
        return new ErroResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
}
