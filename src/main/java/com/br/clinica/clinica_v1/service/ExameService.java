package com.br.clinica.clinica_v1.service;

import com.br.clinica.clinica_v1.repository.ExameRepository;
import com.br.clinica.clinica_v1.entity.Exame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExameService {

    @Autowired
    ExameRepository repository;

    public Exame create(Exame exame) {
        return repository.save(exame);
    }

    public List<Exame> getByMedicoId(Long medicoId) {

        return repository.findByMedicoId(medicoId);
    }
}
