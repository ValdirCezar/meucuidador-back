package com.valdir.meucuidador.repository;

import com.valdir.meucuidador.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
