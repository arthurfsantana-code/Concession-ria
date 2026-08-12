Concessionária Marcelo Gomes - API

API do case da concessionária, entrega 1. Modelagem com JPA, repositories e CRUD básico (sem DTO ainda).

As decisões que tomei em cima do que o case não deixava claro estão no DECISOES.md.

Tecnologias: Java 17, Spring Boot (Web + JPA), H2 pra rodar sem precisar instalar banco.

Pra rodar:
mvn spring-boot:run

Sobe em localhost:8080

Entidades:
Cliente - nome, cpf (único), telefone, email
Carro - modelo, marca, anoFabricacao, anoModelo, cor, placa (pode ser nula), chassi (único), quilometragem, preco, status e o cliente relacionado

Endpoints de Cliente:
POST /clientes
GET /clientes
GET /clientes/{id}
DELETE /clientes/{id}

Endpoints de Carro:
POST /carros
GET /carros
GET /carros/{id}
DELETE /carros/{id}

Exemplo pra cadastrar um carro:

{
  "modelo": "Corolla",
  "marca": "Toyota",
  "anoFabricacao": 2024,
  "anoModelo": 2025,
  "cor": "Prata",
  "chassi": "9BWZZZ377VT004251",
  "quilometragem": 0,
  "preco": 145000.00
}
