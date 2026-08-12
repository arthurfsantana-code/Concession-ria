package com.marcelogomes.concessionaria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marcelogomes.concessionaria.dto.ClienteRequestDTO;
import com.marcelogomes.concessionaria.dto.ClienteResponseDTO;
import com.marcelogomes.concessionaria.exception.RecursoNaoEncontradoException;
import com.marcelogomes.concessionaria.exception.RegistroDuplicadoException;
import com.marcelogomes.concessionaria.model.Cliente;
import com.marcelogomes.concessionaria.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {
        if (repository.existsByCpf(dto.cpf())) {
            throw new RegistroDuplicadoException("Já existe um cliente cadastrado com o CPF " + dto.cpf());
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());

        Cliente salvo = repository.save(cliente);
        return paraResponseDTO(salvo);
    }

    public List<ClienteResponseDTO> listar() {
        return repository.findAll().stream()
                .map(this::paraResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        return paraResponseDTO(cliente);
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Cliente com id " + id + " não encontrado");
        }
        repository.deleteById(id);
    }

    private Cliente buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com id " + id + " não encontrado"));
    }

    private ClienteResponseDTO paraResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEmail()
        );
    }
}
