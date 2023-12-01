package com.br.clinica.clinica_v1.authentication.repository;


import com.br.clinica.clinica_v1.authentication.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByUserName(String userName);
}
