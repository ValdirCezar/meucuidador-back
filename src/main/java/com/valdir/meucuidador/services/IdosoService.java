package com.valdir.meucuidador.services;

import com.valdir.meucuidador.domain.Idoso;
import com.valdir.meucuidador.domain.dto.IdosoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IdosoService {

    Idoso findById(Integer id);

    List<Idoso> findAll();

    Idoso create(IdosoDTO dto);

    Idoso update(IdosoDTO dto, Integer id);

    void delete(Integer id);
}
