package com.marcelogomes.concessionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelogomes.concessionaria.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
