package com.br.clinica.clinica_v1.repository;

import com.br.clinica.clinica_v1.entity.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long>{
    public List<Exame> findByMedicoId(Long medicoId);
}
