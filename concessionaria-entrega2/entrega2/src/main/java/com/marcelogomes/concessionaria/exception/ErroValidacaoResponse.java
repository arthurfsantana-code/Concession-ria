package com.marcelogomes.concessionaria.exception;

import java.util.List;

public record ErroValidacaoResponse(int status, List<ErroCampo> erros) {
}
