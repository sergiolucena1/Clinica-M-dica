package com.br.clinica.clinica_v1.repository;

import com.br.clinica.clinica_v1.entity.RegistroPaciente;
import com.br.clinica.clinica_v1.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroPacienteRepository extends JpaRepository<RegistroPaciente, Long> {
    public List<RegistroPaciente> findByAlunoId(Long alunoId);

    public List<RegistroPaciente> findByStatus(String status);
    @Query(value = "select a from RegistroPaciente r, Paciente p where p.id = r.paciente.id and " +
            " m.status = :status")
    public List<Paciente> retornaPacientes(String status);
}
