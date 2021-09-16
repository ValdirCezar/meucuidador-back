package com.valdir.meucuidador.services.impl;

import com.valdir.meucuidador.domain.Cuidador;
import com.valdir.meucuidador.domain.dto.CuidadorDTO;
import com.valdir.meucuidador.repository.CuidadorRepository;
import com.valdir.meucuidador.services.CuidadorService;
import com.valdir.meucuidador.services.exception.DataIntegratyViolationException;
import com.valdir.meucuidador.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CuidadorServiceImpl implements CuidadorService {

    private static final String JÁ_CADASTRADO_NO_SISTEMA = "já cadastrado no sistema";
    @Autowired
    private CuidadorRepository repository;

    @Override
    public Cuidador findById(Integer id) {
        Optional<Cuidador> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public List<Cuidador> findAll() {
        return repository.findAll();
    }

    @Override
    public Cuidador create(CuidadorDTO dto) {
        dto.setId(null);
        validByCPFEmailAndPhone(dto);
        return repository.save(new Cuidador(dto));
    }

    private void validByCPFEmailAndPhone(CuidadorDTO dto) {
        Optional<Cuidador> cuidador = repository.findByCpf(dto.getCpf());
        if(cuidador.isPresent()) {
            throw new DataIntegratyViolationException("CPF " + JÁ_CADASTRADO_NO_SISTEMA);
        }

        cuidador = repository.findByEmail(dto.getEmail());
        if(cuidador.isPresent()) {
            throw new DataIntegratyViolationException("E-MAIL " + JÁ_CADASTRADO_NO_SISTEMA);
        }

        cuidador = repository.findByPhone(dto.getPhone());
        if(cuidador.isPresent()) {
            throw new DataIntegratyViolationException("TELEFONE " + JÁ_CADASTRADO_NO_SISTEMA);
        }
    }
}
