package com.br.clinica.clinica_v1.service;

import com.br.clinica.clinica_v1.dto.ExamesPacienteDto;
import com.br.clinica.clinica_v1.dto.HistoriocoPacienteDTO;
import com.br.clinica.clinica_v1.dto.RegistroPacienteExamesDto;
import com.br.clinica.clinica_v1.entity.Paciente;
import com.br.clinica.clinica_v1.entity.RegistroPaciente;
import com.br.clinica.clinica_v1.repository.RegistroPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
public class RegistroPacienteService {

    static final Double gradesAvgToApprove = 7.0;

    @Autowired
    RegistroPacienteRepository repository;

    public RegistroPaciente create(RegistroPaciente registroPaciente) {
        registroPaciente.setStatus("REGISTRADO");
        return repository.save(registroPaciente);
    }

    public void cancelarRegistro(Long registroPaciente) throws Exception {
        Optional<RegistroPaciente> registroPacienteToUpdate = repository.findById(registroPaciente);

        if (registroPacienteToUpdate.isPresent()) {
            if (Objects.equals(registroPacienteToUpdate.get().getStatus(), "REGISTRADO")) {
                registroPacienteToUpdate.ifPresent(matriculaAluno -> registroPaciente.setStatus("CANCELADO"));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível cancelar com status REGISTRADO.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "REGISTRO não encontrada.");
        }
        repository.save(registroPacienteToUpdate.get());
    }

    public List<Paciente> retonaPacienteStatus(String status) {

        return repository.retornaPacientes(status);
    }
}
