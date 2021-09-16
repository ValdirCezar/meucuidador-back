package com.valdir.meucuidador.services.impl;

import com.valdir.meucuidador.domain.Cuidador;
import com.valdir.meucuidador.domain.dto.CuidadorDTO;
import com.valdir.meucuidador.domain.enums.Perfil;
import com.valdir.meucuidador.repository.CuidadorRepository;
import com.valdir.meucuidador.services.exception.DataIntegratyViolationException;
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
    private static final int INDEX_0   = 0;

    private static final String JA_CADASTRADO_NO_SISTEMA = "já cadastrado no sistema";

    @InjectMocks
    private CuidadorServiceImpl service;

    @Mock
    private CuidadorRepository repository;

    private Optional<Cuidador> optionalCuidador;
    private Cuidador cuidador;
    private CuidadorDTO cuidadorDTO;

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
        Assertions.assertEquals(response.get(INDEX_0).getId(), list.get(INDEX_0).getId());
        Assertions.assertEquals(response.get(INDEX_0).getNome(), list.get(INDEX_0).getNome());
        Assertions.assertEquals(response.get(INDEX_0).getCpf(), list.get(INDEX_0).getCpf());
        Assertions.assertEquals(response.get(INDEX_0).getEmail(), list.get(INDEX_0).getEmail());
        Assertions.assertEquals(response.get(INDEX_0).getPerfis(), list.get(INDEX_0).getPerfis());
    }

    @Test
    void createSuccessTest() {
        Mockito.when(repository.save(Mockito.any(Cuidador.class))).thenReturn(cuidador);

        Cuidador response = service.create(cuidadorDTO);

        Assertions.assertEquals(response.getId(), cuidador.getId());
        Assertions.assertEquals(response.getNome(), cuidador.getNome());
        Assertions.assertEquals(response.getCpf(), cuidador.getCpf());
        Assertions.assertEquals(response.getEmail(), cuidador.getEmail());
        Assertions.assertEquals(response.getPerfis(), cuidador.getPerfis());
    }

    @Test
    void createWithCPFErrorTest() {
        DataIntegratyViolationException exception
                = new DataIntegratyViolationException("CPF " + JA_CADASTRADO_NO_SISTEMA);

        Mockito.when(repository.findByCpf(Mockito.any())).thenReturn(optionalCuidador);

        try{
            service.create(cuidadorDTO);
        } catch (Exception ex) {
            Assertions.assertEquals(exception.getClass(), ex.getClass());
        }

    }

    @Test
    void createWithEmailErrorTest() {
        DataIntegratyViolationException exception
                = new DataIntegratyViolationException("E-MAIL " + JA_CADASTRADO_NO_SISTEMA);

        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(optionalCuidador);

        try{
            service.create(cuidadorDTO);
        } catch (Exception ex) {
            Assertions.assertEquals(exception.getClass(), ex.getClass());
        }

    }

    @Test
    void createWithPhoneErrorTest() {
        DataIntegratyViolationException exception
                = new DataIntegratyViolationException("TELEFONE " + JA_CADASTRADO_NO_SISTEMA);

        Mockito.when(repository.findByPhone(Mockito.any())).thenReturn(optionalCuidador);

        try{
            service.create(cuidadorDTO);
        } catch (Exception ex) {
            Assertions.assertEquals(exception.getClass(), ex.getClass());
        }

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

        cuidadorDTO = new CuidadorDTO(cuidador);
    }

}