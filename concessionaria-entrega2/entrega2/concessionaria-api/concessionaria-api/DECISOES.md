Decisões

Cor do carro: deixei String livre, não enum. Cada marca chama a cor de um jeito diferente, ia ser difícil fechar uma lista.

Placa: não é obrigatória, porque carro novo às vezes chega sem placa (só sai depois do emplacamento). Mas não pode repetir.

Chassi: esse já é obrigatório e único, porque todo carro tem chassi desde que chega.

Ano de fabricação e ano do modelo: são dois campos separados porque o case fala que não é a mesma coisa.

Preço: usei BigDecimal em vez de double pra não ter problema de arredondamento com dinheiro.

Status do carro: fiz um enum com disponível, reservado e vendido. É basicamente o problema que o case descreve (o Corolla foi negociado duas vezes porque não tinha esse controle).

Cliente dentro do carro: coloquei um campo de cliente no Carro pra saber quem reservou/comprou. Fica nulo enquanto está disponível. Ainda não bloqueei reserva duplicada, isso fica pra próxima entrega.

CPF: String, não número, porque pode ter zero na frente. É único porque o case fala que não repete.

Telefone e email: os dois são obrigatórios porque a promoção é mandada pelos dois. Não são únicos, só o CPF foi citado como único.

Vendedor: não criei essa entidade porque o case cita vendedor mas não fala nenhum atributo dele. Se precisar dá pra adicionar depois.
