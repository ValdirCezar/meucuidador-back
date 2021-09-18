package com.valdir.meucuidador.resources;

import com.valdir.meucuidador.domain.dto.CuidadorDTO;
import com.valdir.meucuidador.services.impl.CuidadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cuidadores")
public class CuidadorResource {

    @Autowired
    private CuidadorServiceImpl service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CuidadorDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new CuidadorDTO(service.findById(id)));
    }
}
