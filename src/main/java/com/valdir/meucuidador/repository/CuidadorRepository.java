package com.valdir.meucuidador.repository;

import com.valdir.meucuidador.domain.Cuidador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuidadorRepository extends JpaRepository<Cuidador, Integer> {
}
