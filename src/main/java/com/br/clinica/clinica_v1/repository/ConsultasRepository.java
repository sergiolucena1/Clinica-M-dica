package com.br.clinica.clinica_v1.repository;

import com.br.clinica.clinica_v1.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultasRepository  extends JpaRepository<Consulta, Long> {
}
