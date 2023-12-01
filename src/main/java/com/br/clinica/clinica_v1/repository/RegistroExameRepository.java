package com.br.clinica.clinica_v1.repository;

import com.br.clinica.clinica_v1.entity.RegistroExame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroExameRepository extends JpaRepository<RegistroExame, Long>  {
    RegistroExame findByCpfAndExameId(Long cpf, Long exameId);

    List<RegistroExame> findByExameId(Long exameId);
}
