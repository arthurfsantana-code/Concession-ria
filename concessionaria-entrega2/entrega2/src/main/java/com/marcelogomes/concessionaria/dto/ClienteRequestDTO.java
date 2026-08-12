package com.marcelogomes.concessionaria.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * DTO de entrada do Cliente.
 * O id fica de fora: quem gera é o banco, o cliente da API nunca manda id.
 */
public record ClienteRequestDTO(

        @NotBlank(message = "nome é obrigatório")
        String nome,

        // exatamente 11 dígitos numéricos, sem letra, sem ponto/traço
        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 números, sem letras ou pontuação")
        String cpf,

        @NotBlank(message = "telefone é obrigatório")
        String telefone,

        @NotBlank(message = "email é obrigatório")
        @Email(message = "email em formato inválido")
        String email
) {
}
