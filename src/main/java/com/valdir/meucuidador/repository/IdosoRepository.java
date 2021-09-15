package com.valdir.meucuidador.repository;

import com.valdir.meucuidador.domain.Idoso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdosoRepository extends JpaRepository<Idoso, Integer> {
}
