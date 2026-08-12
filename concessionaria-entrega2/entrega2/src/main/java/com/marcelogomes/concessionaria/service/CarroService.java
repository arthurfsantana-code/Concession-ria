package com.marcelogomes.concessionaria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marcelogomes.concessionaria.dto.CarroRequestDTO;
import com.marcelogomes.concessionaria.dto.CarroResponseDTO;
import com.marcelogomes.concessionaria.exception.RecursoNaoEncontradoException;
import com.marcelogomes.concessionaria.exception.RegistroDuplicadoException;
import com.marcelogomes.concessionaria.model.Carro;
import com.marcelogomes.concessionaria.model.StatusCarro;
import com.marcelogomes.concessionaria.repository.CarroRepository;

@Service
public class CarroService {

    private final CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public CarroResponseDTO cadastrar(CarroRequestDTO dto) {
        if (repository.existsByChassi(dto.chassi())) {
            throw new RegistroDuplicadoException("Já existe um carro cadastrado com o chassi " + dto.chassi());
        }
        if (dto.placa() != null && !dto.placa().isBlank() && repository.existsByPlaca(dto.placa())) {
            throw new RegistroDuplicadoException("Já existe um carro cadastrado com a placa " + dto.placa());
        }

        Carro carro = new Carro();
        carro.setModelo(dto.modelo());
        carro.setMarca(dto.marca());
        carro.setAnoFabricacao(dto.anoFabricacao());
        carro.setAnoModelo(dto.anoModelo());
        carro.setCor(dto.cor());
        carro.setPlaca(dto.placa());
        carro.setChassi(dto.chassi());
        carro.setQuilometragem(dto.quilometragem());
        carro.setPreco(dto.preco());
        // status e cliente não vêm do DTO de entrada: todo carro novo entra DISPONIVEL
        // e sem cliente, é sempre assim que o negócio começa
        carro.setStatus(StatusCarro.DISPONIVEL);
        carro.setCliente(null);

        Carro salvo = repository.save(carro);
        return paraResponseDTO(salvo);
    }

    public List<CarroResponseDTO> listar() {
        return repository.findAll().stream()
                .map(this::paraResponseDTO)
                .toList();
    }

    public CarroResponseDTO buscarPorId(Long id) {
        Carro carro = buscarEntidadePorId(id);
        return paraResponseDTO(carro);
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Carro com id " + id + " não encontrado");
        }
        repository.deleteById(id);
    }

    private Carro buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro com id " + id + " não encontrado"));
    }

    private CarroResponseDTO paraResponseDTO(Carro carro) {
        Long clienteId = carro.getCliente() != null ? carro.getCliente().getId() : null;
        String clienteNome = carro.getCliente() != null ? carro.getCliente().getNome() : null;

        return new CarroResponseDTO(
                carro.getId(),
                carro.getModelo(),
                carro.getMarca(),
                carro.getAnoFabricacao(),
                carro.getAnoModelo(),
                carro.getCor(),
                carro.getPlaca(),
                carro.getChassi(),
                carro.getQuilometragem(),
                carro.getPreco(),
                carro.getStatus(),
                clienteId,
                clienteNome
        );
    }
}
