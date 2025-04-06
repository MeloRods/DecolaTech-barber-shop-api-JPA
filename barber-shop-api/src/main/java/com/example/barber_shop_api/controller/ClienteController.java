package com.example.barber_shop_api.controller;

import com.example.barber_shop_api.model.Cliente;
import com.example.barber_shop_api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    // Criar um novo cliente
    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Atualizar um cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            clienteRepository.save(cliente);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    // Deletar um cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            clienteRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}