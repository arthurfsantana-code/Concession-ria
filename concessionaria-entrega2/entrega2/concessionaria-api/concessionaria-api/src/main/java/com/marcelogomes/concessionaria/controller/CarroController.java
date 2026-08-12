package com.marcelogomes.concessionaria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelogomes.concessionaria.model.Carro;
import com.marcelogomes.concessionaria.repository.CarroRepository;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroRepository repository;

    public CarroController(CarroRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Carro cadastrar(@RequestBody Carro carro) {
        return repository.save(carro);
    }

    @GetMapping
    public List<Carro> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Carro buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
