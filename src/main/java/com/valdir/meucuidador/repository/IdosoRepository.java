package com.valdir.meucuidador.repository;

import com.valdir.meucuidador.domain.Idoso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdosoRepository extends JpaRepository<Idoso, Integer> {
    Optional<Idoso> findByCpf(String cpf);
    Optional<Idoso> findByEmail(String email);
    Optional<Idoso> findByPhone(String phone);
}
