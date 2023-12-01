package com.br.clinica.clinica_v1.service;

import com.br.clinica.clinica_v1.entity.Consulta;
import com.br.clinica.clinica_v1.entity.Paciente;
import com.br.clinica.clinica_v1.repository.ConsultasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
@Service
public class ConsultaService {

    @Autowired
    ConsultasRepository repository;

    @PersistenceContext
    private EntityManager entityManager;
    public Consulta create(Consulta consulta) {
        return repository.save(consulta);
    }

    public List<Consulta> findAll() {
        return repository.findAll();
    }

    public Optional<Consulta> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Paciente> buscarListaPaciente(Long id) {
        Query query = entityManager.createNativeQuery("select a.* from clinica_v1.consulta c" +
                "  inner join clinica_v1 ac on c.id = ac.id_consulta" +
                "  inner join  clinica_v1.paciente a on ac.id_paciente = a.id" +
                "  where c.id = " + id, "PacientesConsultasMapping");
        List<Paciente> pessoas = query.getResultList();
        return pessoas;
    }
}
