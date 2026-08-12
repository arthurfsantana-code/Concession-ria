package com.marcelogomes.concessionaria.dto;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String telefone,
        String email
) {
}
