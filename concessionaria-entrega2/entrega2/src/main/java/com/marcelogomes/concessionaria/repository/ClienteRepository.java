package com.marcelogomes.concessionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelogomes.concessionaria.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // usado no service pra barrar CPF duplicado antes de salvar (Entrega 2)
    boolean existsByCpf(String cpf);
}
