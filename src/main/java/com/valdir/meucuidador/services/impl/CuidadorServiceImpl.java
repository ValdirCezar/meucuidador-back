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

    private static final String JA_CADASTRADO_NO_SISTEMA = "já cadastrado no sistema";
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
        validByCPFEmailAndPhone(dto);
        return repository.save(new Cuidador(dto));
    }

    @Override
    public Cuidador update(CuidadorDTO dto, Integer id) {
        dto.setId(id);
        Cuidador obj = findById(dto.getId());
        validByCPFEmailAndPhone(dto);
        obj = new Cuidador(dto);
        return repository.save(obj);
    }

    private void validByCPFEmailAndPhone(CuidadorDTO dto) {
        Optional<Cuidador> cuidador = repository.findByCpf(dto.getCpf());
        if(cuidador.isPresent() && !dto.getId().equals(cuidador.get().getId())) {
            throw new DataIntegratyViolationException("CPF " + JA_CADASTRADO_NO_SISTEMA);
        }

        cuidador = repository.findByEmail(dto.getEmail());
        if(cuidador.isPresent() && !dto.getId().equals(cuidador.get().getId())) {
            throw new DataIntegratyViolationException("E-MAIL " + JA_CADASTRADO_NO_SISTEMA);
        }

        cuidador = repository.findByPhone(dto.getPhone());
        if(cuidador.isPresent() && !dto.getId().equals(cuidador.get().getId())) {
            throw new DataIntegratyViolationException("TELEFONE " + JA_CADASTRADO_NO_SISTEMA);
        }
    }
}
