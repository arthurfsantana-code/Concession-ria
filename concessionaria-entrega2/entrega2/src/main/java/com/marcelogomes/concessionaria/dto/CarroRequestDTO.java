package com.marcelogomes.concessionaria.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada do Carro.
 *
 * Campos que ficaram de fora de propósito (quem cadastra não deveria definir):
 * - id: quem gera é o banco.
 * - status: todo carro entra como DISPONIVEL. Mudar de status (reservar/vender) é
 *   uma ação de negócio, não um dado que se digita no cadastro.
 * - cliente: pela mesma razão do status, associar um cliente é uma ação futura
 *   (reserva/venda), não parte do cadastro do carro.
 */
public record CarroRequestDTO(

        @NotBlank(message = "modelo é obrigatório")
        String modelo,

        @NotBlank(message = "marca é obrigatória")
        String marca,

        @NotNull(message = "ano de fabricação é obrigatório")
        @Min(value = 1950, message = "ano de fabricação inválido")
        @Max(value = 2026, message = "ano de fabricação não pode ser maior que 2026")
        Integer anoFabricacao,

        @NotNull(message = "ano do modelo é obrigatório")
        @Min(value = 1950, message = "ano do modelo inválido")
        @Max(value = 2027, message = "ano do modelo não pode ser maior que 2027")
        Integer anoModelo,

        @NotBlank(message = "cor é obrigatória")
        String cor,

        // placa não é obrigatória (carro zero pode chegar sem placa), mas se vier
        // tem que ter formato de placa de verdade (Mercosul ou padrão antigo)
        @Pattern(
                regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$",
                message = "placa deve seguir o formato ABC1234 ou ABC1D23"
        )
        String placa,

        @NotBlank(message = "chassi é obrigatório")
        @Size(min = 17, max = 17, message = "chassi deve ter exatamente 17 caracteres")
        String chassi,

        @NotNull(message = "quilometragem é obrigatória")
        @PositiveOrZero(message = "quilometragem não pode ser negativa")
        Integer quilometragem,

        @NotNull(message = "preço é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "preço deve ser maior que 0")
        BigDecimal preco
) {

    // regra implícita: ano do modelo não pode ser anterior ao ano de fabricação
    // nem "pular" mais de um ano à frente (ex: fabricado em 2024 não vira modelo 2027)
    @AssertTrue(message = "ano do modelo deve ser igual ou até 1 ano maior que o ano de fabricação")
    public boolean isAnoModeloCoerente() {
        if (anoFabricacao == null || anoModelo == null) {
            return true; // @NotNull já cuida de avisar isso separadamente
        }
        return anoModelo >= anoFabricacao && anoModelo <= anoFabricacao + 1;
    }
}
