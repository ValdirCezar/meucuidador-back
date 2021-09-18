package com.valdir.meucuidador.services.impl;

import com.valdir.meucuidador.domain.Idoso;
import com.valdir.meucuidador.domain.dto.IdosoDTO;
import com.valdir.meucuidador.repository.IdosoRepository;
import com.valdir.meucuidador.services.IdosoService;
import com.valdir.meucuidador.services.exception.DataIntegratyViolationException;
import com.valdir.meucuidador.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdosoServiceImpl implements IdosoService {

    private static final String JA_CADASTRADO_NO_SISTEMA = "já cadastrado no sistema";

    @Autowired
    private IdosoRepository repository;

    @Override
    public Idoso findById(Integer id) {
        Optional<Idoso> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public List<Idoso> findAll() { return repository.findAll(); }

    @Override
    public Idoso create(IdosoDTO dto) {
        validByCPFEmailAndPhone(dto);
        return repository.save(new Idoso(dto));
    }

    @Override
    public Idoso update(IdosoDTO dto, Integer id) {
        dto.setId(id);
        Idoso obj = findById(dto.getId());
        validByCPFEmailAndPhone(dto);
        obj = new Idoso(dto);
        return repository.save(obj);
    }

    @Override
    public void delete(Integer id) { repository.deleteById(id); }

    private void validByCPFEmailAndPhone(IdosoDTO dto) {
        Optional<Idoso> idoso = repository.findByCpf(dto.getCpf());
        if(idoso.isPresent() && !dto.getId().equals(idoso.get().getId())) {
            throw new DataIntegratyViolationException("CPF " + JA_CADASTRADO_NO_SISTEMA);
        }

        idoso = repository.findByEmail(dto.getEmail());
        if(idoso.isPresent() && !dto.getId().equals(idoso.get().getId())) {
            throw new DataIntegratyViolationException("E-MAIL " + JA_CADASTRADO_NO_SISTEMA);
        }

        idoso = repository.findByPhone(dto.getPhone());
        if(idoso.isPresent() && !dto.getId().equals(idoso.get().getId())) {
            throw new DataIntegratyViolationException("TELEFONE " + JA_CADASTRADO_NO_SISTEMA);
        }
    }
}
