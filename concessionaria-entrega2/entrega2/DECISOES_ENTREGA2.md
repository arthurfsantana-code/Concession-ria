# Decisões — Entrega 2 (DTOs e Validação)

## O que ficou de fora do DTO de entrada

**Carro**
- `id`: quem gera é o banco, cliente da API nunca manda.
- `status`: todo carro cadastrado entra como `DISPONIVEL`. Mudar de status (reservar,
  vender) é uma ação de negócio que devia ter seu próprio endpoint no futuro, não um
  campo livre no cadastro — senão dá pra cadastrar um carro já "VENDIDO" direto.
- `cliente`: mesma lógica do status. Vincular cliente é consequência de uma reserva/venda,
  não parte do cadastro do carro. Fica nulo até existir esse fluxo (que é da próxima entrega).

**Cliente**
- `id`: mesma razão do carro.

## Regras de validação — o que o case pede explicitamente
- Ano 2202 não pode → `@Max(2026)` em `anoFabricacao` (e `@Max(2027)` em `anoModelo`,
  já que modelo costuma vir um ano à frente da fabricação).
- Preço zero não pode → `@DecimalMin(value = "0.0", inclusive = false)`, ou seja, tem
  que ser maior que zero.
- CPF com letra no meio não pode → `@Pattern(regexp = "\\d{11}")`, só aceita 11 dígitos.

## Regras derivadas (implícitas no case)
- Campos obrigatórios: tudo que já era `nullable = false` na entidade virou `@NotBlank`/
  `@NotNull` no DTO (modelo, marca, anos, cor, chassi, quilometragem, preço, nome,
  CPF, telefone, email).
- `placa` continua opcional (carro zero pode não ter placa ainda), mas se vier, precisa
  ter formato de placa (Mercosul ou padrão antigo) — `@Pattern`.
- `chassi` tem que ter 17 caracteres, que é o tamanho padrão de um VIN de verdade —
  `@Size(min = 17, max = 17)`.
- `quilometragem` não pode ser negativa, mas pode ser zero (carro novo) — `@PositiveOrZero`.
- Adicionei uma checagem extra (`@AssertTrue`) pra garantir que `anoModelo` não seja
  menor que `anoFabricacao` nem "pule" mais de 1 ano à frente. O case não fala isso
  de forma explícita, mas é uma regra que qualquer vendedor ia esperar que existisse.

## Service e camada de acesso
- Criei `CarroService` e `ClienteService`. Os controllers não enxergam mais o
  `Repository` — só o Service conversa com ele. Isso também é onde ficou a checagem
  de duplicidade (chassi, placa, CPF) antes de tentar salvar, pra devolver um 409
  com mensagem clara em vez de estourar uma exceção de banco.

## Erros
- Segui exatamente o formato pedido pro erro de validação (400): `status` + lista de
  `erros` com `campo` e `mensagem`.
- Para 404 (recurso não encontrado) e 409 (chassi/placa/CPF duplicado) usei um formato
  mais simples, `status` + `mensagem`, já que não faz sentido falar de "campo" nesses casos.
- As mensagens foram escritas pensando em alguém no balcão lendo o erro, não em
  quem programou (ex: "preço deve ser maior que 0", não "value must be > 0").

## O que fica pra próxima entrega
- Endpoint de reservar/vender carro (é aí que `status` e `cliente` vão ser preenchidos).
- Bloquear reserva duplicada, como já estava anotado no `DECISOES.md` de ontem.
