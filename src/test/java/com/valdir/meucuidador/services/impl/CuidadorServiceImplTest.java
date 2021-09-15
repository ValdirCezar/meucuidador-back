package com.valdir.meucuidador.services.impl;

import com.valdir.meucuidador.domain.Cuidador;
import com.valdir.meucuidador.domain.enums.Perfil;
import com.valdir.meucuidador.repository.CuidadorRepository;

import com.valdir.meucuidador.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CuidadorServiceImplTest {

    private static final Integer ID = 1;

    @InjectMocks
    private CuidadorServiceImpl service;

    @Mock
    private CuidadorRepository repository;

    private final Optional<Cuidador> cuidador = Optional.of(new Cuidador());

    @BeforeEach
    void setUp() {
        iniciaCuidador();
    }

    @Test
    void findByIdSuccessTest() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(this.cuidador);

        Cuidador response = service.findById(ID);

        if(cuidador.isPresent()) {
            Assertions.assertEquals(response.getId(), cuidador.get().getId());
            Assertions.assertEquals(response.getNome(), cuidador.get().getNome());
            Assertions.assertEquals(response.getCpf(), cuidador.get().getCpf());
            Assertions.assertEquals(response.getEmail(), cuidador.get().getEmail());
            Assertions.assertEquals(response.getPerfis(), cuidador.get().getPerfis());
        }
    }

    @Test
    void findByIdErrorTest() {
        ObjectNotFoundException notFoundException = new ObjectNotFoundException("Objeto não encontrado");
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(notFoundException);

        try{
            Cuidador response = service.findById(ID);
        } catch (Exception ex) {
            Assertions.assertEquals(notFoundException.getClass(), ex.getClass());
            Assertions.assertEquals(notFoundException.getMessage(), ex.getMessage());
        }
    }

    private void iniciaCuidador() {
        if(cuidador.isPresent()) {
            cuidador.get().setId(ID);
            cuidador.get().setNome("Valdir Cezar");
            cuidador.get().setCpf("66090972088");
            cuidador.get().setEmail("email@test.com");
            cuidador.get().addPerfil(Perfil.CUIDADOR);
        }
    }

}