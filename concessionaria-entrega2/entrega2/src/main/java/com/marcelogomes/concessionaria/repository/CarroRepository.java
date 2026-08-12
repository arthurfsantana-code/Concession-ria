package com.marcelogomes.concessionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelogomes.concessionaria.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    // usados no service pra barrar chassi/placa duplicados antes de salvar (Entrega 2)
    boolean existsByChassi(String chassi);

    boolean existsByPlaca(String placa);
}
