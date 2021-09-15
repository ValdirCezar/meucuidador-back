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

import java.util.List;
import java.util.Optional;

@SpringBootTest
class CuidadorServiceImplTest {

    private static final Integer ID    = 1;
    private static final String NOME   = "Valdir Cezar";
    private static final String CPF    = "66090972088";
    private static final String EMAIL  = "email@test.com";
    private static final Perfil PERFIL = Perfil.CUIDADOR;

    @InjectMocks
    private CuidadorServiceImpl service;

    @Mock
    private CuidadorRepository repository;

    private Optional<Cuidador> optionalCuidador;
    private Cuidador cuidador;

    @BeforeEach
    void setUp() {
        iniciaOptionalCuidador();
        iniciaCuidador();
    }

    @Test
    void findByIdSuccessTest() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(this.optionalCuidador);

        Cuidador response = service.findById(ID);

        if(optionalCuidador.isPresent()) {
            Assertions.assertEquals(response.getId(), optionalCuidador.get().getId());
            Assertions.assertEquals(response.getNome(), optionalCuidador.get().getNome());
            Assertions.assertEquals(response.getCpf(), optionalCuidador.get().getCpf());
            Assertions.assertEquals(response.getEmail(), optionalCuidador.get().getEmail());
            Assertions.assertEquals(response.getPerfis(), optionalCuidador.get().getPerfis());
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

    @Test
    void findAllTest() {
        List<Cuidador> list = List.of(cuidador);

        Mockito.when(repository.findAll()).thenReturn(list);
        List<Cuidador> response = service.findAll();

        Assertions.assertEquals(response.size(), list.size());
        Assertions.assertEquals(response.get(0).getId(), list.get(0).getId());
        Assertions.assertEquals(response.get(0).getNome(), list.get(0).getNome());
        Assertions.assertEquals(response.get(0).getCpf(), list.get(0).getCpf());
        Assertions.assertEquals(response.get(0).getEmail(), list.get(0).getEmail());
        Assertions.assertEquals(response.get(0).getPerfis(), list.get(0).getPerfis());
    }

    private void iniciaOptionalCuidador() {
        optionalCuidador = Optional.of(new Cuidador());

        optionalCuidador.get().setId(ID);
        optionalCuidador.get().setNome(NOME);
        optionalCuidador.get().setCpf(CPF);
        optionalCuidador.get().setEmail(EMAIL);
        optionalCuidador.get().addPerfil(PERFIL);
    }

    private void iniciaCuidador() {
        cuidador = new Cuidador();
        cuidador.setId(ID);
        cuidador.setNome(NOME);
        cuidador.setCpf(CPF);
        cuidador.setEmail(EMAIL);
        cuidador.addPerfil(PERFIL);
    }

}