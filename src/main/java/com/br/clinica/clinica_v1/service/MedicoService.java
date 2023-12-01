package com.br.clinica.clinica_v1.service;

import com.br.clinica.clinica_v1.entity.Medico;
import com.br.clinica.clinica_v1.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    @Autowired
    MedicoRepository repository;

    public Medico create(Medico professor) {
        return repository.save(professor);
    }
}
