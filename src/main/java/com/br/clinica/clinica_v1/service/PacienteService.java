package com.br.clinica.clinica_v1.service;


import com.br.clinica.clinica_v1.entity.Paciente;
import com.br.clinica.clinica_v1.repository.PacienteReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteReprository repository;

    public Paciente create(Paciente paciente) {
        return repository.save(paciente);
    }

    public Optional<Package> findById(Long id) {
        return repository.findById(id);
    }


    public List<Package> listaTodos() {
        return repository.findAll();
    }

    public List<Paciente> listaPorNome(String nome) {
        return repository.findByNomeContainsIgnoreCase(nome);
    }

    public List<Paciente> listarPacienteConsulta(Long idDiciplina) {
        return repository.listaPacienteConsulta(idDiciplina);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}