package com.br.clinica.clinica_v1.controller;

import com.br.clinica.clinica_v1.entity.Paciente;
import com.br.clinica.clinica_v1.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService service;

    @GetMapping("/listar-todos")
    @ResponseStatus(HttpStatus.OK)
    public List<Package> findAll() {
        return service.listaTodos();
    }

    @GetMapping("/listar-por-nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public List<Paciente> buscarNome(@PathVariable String nome) {
        return service.listaPorNome(nome);
    }

    @GetMapping("/listar-paciente-consulta/{idConsulta}")
    @ResponseStatus(HttpStatus.OK)
    public List<Paciente> listarPacienteConsulta(@PathVariable Long idDiciplina) {
        return service.listarPacienteConsulta(idDiciplina);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
        Paciente pacienteCreated = service.create(paciente);

        return ResponseEntity.status(201).body(pacienteCreated);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Paciente> update(@RequestBody Paciente paciente) {
        Paciente pacienteCreated = service.create(paciente);

        return ResponseEntity.status(201).body(pacienteCreated);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Package> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}