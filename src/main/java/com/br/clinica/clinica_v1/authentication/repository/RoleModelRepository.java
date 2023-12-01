package com.br.clinica.clinica_v1.authentication.repository;


import com.br.clinica.clinica_v1.authentication.model.RoleModel;
import com.br.clinica.clinica_v1.enuns.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleModelRepository extends JpaRepository<RoleModel, Long> {

    public List<RoleModel> findByRoleNameIn(List<RoleName> roles);

    public RoleModel findByRoleName(String nome);

}
