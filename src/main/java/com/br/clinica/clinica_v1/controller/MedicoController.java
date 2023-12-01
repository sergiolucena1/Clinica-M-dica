package com.br.clinica.clinica_v1.controller;

import com.br.clinica.clinica_v1.entity.Medico;
import com.br.clinica.clinica_v1.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    MedicoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Medico> create(@RequestBody Medico medico) {
        Medico medicoCreated = service.create(medico);

        return ResponseEntity.status(201).body(medicoCreated);
    }
}
