package com.valdir.meucuidador.services;

import com.valdir.meucuidador.domain.Cuidador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CuidadorService {

    Cuidador findById(Integer id);

    List<Cuidador> findAll();
}
