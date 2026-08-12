package com.marcelogomes.concessionaria.dto;

import java.math.BigDecimal;

import com.marcelogomes.concessionaria.model.StatusCarro;

/**
 * DTO de saída do Carro. Mostra o cliente relacionado apenas como id + nome
 * (não expõe CPF/telefone/email do cliente dentro do carro).
 */
public record CarroResponseDTO(
        Long id,
        String modelo,
        String marca,
        Integer anoFabricacao,
        Integer anoModelo,
        String cor,
        String placa,
        String chassi,
        Integer quilometragem,
        BigDecimal preco,
        StatusCarro status,
        Long clienteId,
        String clienteNome
) {
}
