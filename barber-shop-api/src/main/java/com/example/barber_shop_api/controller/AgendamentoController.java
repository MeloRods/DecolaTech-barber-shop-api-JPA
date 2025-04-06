package com.example.barber_shop_api.controller;

import com.example.barber_shop_api.model.Agendamento;
import com.example.barber_shop_api.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping
    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }
    @GetMapping("/{year}/{month}")
    public List<Agendamento> listarAgendamentosPorMes(@PathVariable int year, @PathVariable int month) {
        return agendamentoRepository.findByYearAndMonth(year, month);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> obterAgendamentoPorId(@PathVariable Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElse(null);
        if (agendamento != null) {
            return ResponseEntity.ok(agendamento);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
    @PostMapping
    public Agendamento criarAgendamento(@RequestBody Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamentoAtualizado) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElse(null);
        if (agendamento != null) {
            agendamento.setCliente(agendamentoAtualizado.getCliente());
            agendamento.setDataHora(agendamentoAtualizado.getDataHora());
            agendamento.setServico(agendamentoAtualizado.getServico());
            agendamentoRepository.save(agendamento);
            return ResponseEntity.ok(agendamento);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElse(null);
        if (agendamento != null) {
            agendamentoRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}