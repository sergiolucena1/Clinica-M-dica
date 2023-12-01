package com.br.clinica.clinica_v1.controller;

import com.br.clinica.clinica_v1.entity.Exame;
import com.br.clinica.clinica_v1.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exame")
public class ExameController {

    @Autowired
    ExameService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Exame> create(@RequestBody Exame exame) {
        Exame exameCreated = service.create(exame);

        return ResponseEntity.status(201).body(exameCreated);
    }

    @GetMapping("/medico/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Exame> getByMedicoId(@PathVariable Long id) {
        return service.getByMedicoId(id);
    }

}
