package com.valdir.meucuidador.repository;

import com.valdir.meucuidador.domain.Cuidador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuidadorRepository extends JpaRepository<Cuidador, Integer> {

    Optional<Cuidador> findByCpf(String cpf);
    Optional<Cuidador> findByEmail(String email);
    Optional<Cuidador> findByPhone(String phone);
}
