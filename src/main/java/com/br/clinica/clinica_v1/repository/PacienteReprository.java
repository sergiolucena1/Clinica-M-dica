package com.br.clinica.clinica_v1.repository;

import com.br.clinica.clinica_v1.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteReprository extends JpaRepository<Package, Long> {
    List<Paciente> findByNomeContainsIgnoreCase(String nome);

    @Query(value = "select " +
            "    a.* " +
            "    from " +
            "    clinica.clinica_v1 c " +
            "    inner join id_paciente ip on " +
            "    ip.id = c.id " +
            "    inner join consultas c on " +
            "    c.id = ip.consultas_id " +
            "            where " +
            "    d.id = ?1", nativeQuery = true)
    public List<Paciente> listaPacienteConsulta(Long idConsulta);

}
