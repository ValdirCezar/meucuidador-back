package com.valdir.meucuidador.services.impl;

import com.valdir.meucuidador.domain.Cuidador;
import com.valdir.meucuidador.repository.CuidadorRepository;
import com.valdir.meucuidador.services.CuidadorService;
import com.valdir.meucuidador.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CuidadorServiceImpl implements CuidadorService {

    @Autowired
    private CuidadorRepository repository;

    @Override
    public Cuidador findById(Integer id) {
        Optional<Cuidador> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
