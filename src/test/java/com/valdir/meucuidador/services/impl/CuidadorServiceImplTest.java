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

    private static final Integer ID        = 1;
    private static final Integer ID_2      = 2;
    private static final String NOME       = "Valdir Cezar";
    private static final String CPF        = "66090972088";
    private static final String EMAIL      = "email@test.com";
    private static final Perfil PERFIL     = Perfil.CUIDADOR;
    private static final Integer INDEX_0   = 0;
    private static final String SOBRE      = "Sobre";
    private static final String SENHA      = "123";
    private static final String PHONE      = "43984634308";

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
        iniciaCuidadorDTO();
    }

    @Test
    void findByIdSuccessTest() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(this.optionalCuidador);

        Cuidador response = service.findById(ID);

        if(optionalCuidador.isPresent()) {
            Assertions.assertEquals(optionalCuidador.get().getId(), response.getId());
            Assertions.assertEquals(optionalCuidador.get().getNome(), response.getNome());
            Assertions.assertEquals(optionalCuidador.get().getCpf(), response.getCpf());
            Assertions.assertEquals(optionalCuidador.get().getEmail(), response.getEmail());
            Assertions.assertEquals(optionalCuidador.get().getPhone(), response.getPhone());
            Assertions.assertEquals(optionalCuidador.get().getSenha(), response.getSenha());
            Assertions.assertEquals(optionalCuidador.get().getSobre(), response.getSobre());
            Assertions.assertEquals(optionalCuidador.get().getPerfis(), response.getPerfis());
        }
    }

    @Test
    void findByIdErrorTest() {
        ObjectNotFoundException notFoundException = new ObjectNotFoundException("Objeto não encontrado");
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(notFoundException);

        try{
            service.findById(ID);
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
        Assertions.assertEquals(response.get(INDEX_0).getPhone(), list.get(INDEX_0).getPhone());
        Assertions.assertEquals(response.get(INDEX_0).getSenha(), list.get(INDEX_0).getSenha());
        Assertions.assertEquals(response.get(INDEX_0).getSobre(), list.get(INDEX_0).getSobre());
        Assertions.assertEquals(response.get(INDEX_0).getPerfis(), list.get(INDEX_0).getPerfis());
    }

    @Test
    void createSuccessTest() {
        Mockito.when(repository.save(Mockito.any(Cuidador.class))).thenReturn(cuidador);

        Cuidador response = service.create(cuidadorDTO);

        Assertions.assertEquals(cuidador.getId(), response.getId());
        Assertions.assertEquals(cuidador.getNome(), response.getNome());
        Assertions.assertEquals(cuidador.getCpf(), response.getCpf());
        Assertions.assertEquals(cuidador.getEmail(), response.getEmail());
        Assertions.assertEquals(cuidador.getPhone(), response.getPhone());
        Assertions.assertEquals(cuidador.getSenha(), response.getSenha());
        Assertions.assertEquals(cuidador.getSobre(), response.getSobre());
        Assertions.assertEquals(cuidador.getPerfis(), response.getPerfis());
    }
    

    @Test
    void createWithCPFErrorTest() {
        DataIntegratyViolationException exception
                = new DataIntegratyViolationException("CPF " + JA_CADASTRADO_NO_SISTEMA);

        Mockito.when(repository.findByCpf(Mockito.any())).thenReturn(optionalCuidador);

        try{
            cuidadorDTO.setId(ID_2);
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
            cuidadorDTO.setId(ID_2);
            service.create(cuidadorDTO);
        } catch (Exception ex) {
            Assertions.assertEquals(exception.getClass(), ex.getClass());
        }

    }

    @Test
    void createWithPhoneErrorTest() {

        Mockito.when(repository.findByPhone(Mockito.any())).thenReturn(optionalCuidador);

        try{
            cuidadorDTO.setId(ID_2);
            service.create(cuidadorDTO);
        } catch (Exception ex) {
            Assertions.assertEquals(DataIntegratyViolationException.class, ex.getClass());
        }

    }

    @Test
    void updateWithSuccessTest() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalCuidador);
        Mockito.when(repository.save(Mockito.any())).thenReturn(cuidador);

        Cuidador response = service.update(cuidadorDTO, ID);

        Assertions.assertEquals(cuidador.getId(), response.getId());
        Assertions.assertEquals(cuidador.getNome(), response.getNome());
        Assertions.assertEquals(cuidador.getCpf(), response.getCpf());
        Assertions.assertEquals(cuidador.getEmail(), response.getEmail());
        Assertions.assertEquals(cuidador.getPhone(), response.getPhone());
        Assertions.assertEquals(cuidador.getSenha(), response.getSenha());
        Assertions.assertEquals(cuidador.getSobre(), response.getSobre());
        Assertions.assertEquals(cuidador.getPerfis(), response.getPerfis());
    }

    @Test
    void updateWithObjectNotFoundErrorTest() {
        try {
            service.update(cuidadorDTO, ID);
        } catch (Exception ex) {
            Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
            Assertions.assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    private void iniciaOptionalCuidador() {
        optionalCuidador = Optional.of(new Cuidador());

        optionalCuidador.get().setId(ID);
        optionalCuidador.get().setNome(NOME);
        optionalCuidador.get().setCpf(CPF);
        optionalCuidador.get().setEmail(EMAIL);
        optionalCuidador.get().setPhone(PHONE);
        optionalCuidador.get().setSenha(SENHA);
        optionalCuidador.get().addPerfil(PERFIL);
        optionalCuidador.get().setSobre(SOBRE);
    }

    private void iniciaCuidador() {
        cuidador = new Cuidador();
        cuidador.setId(ID);
        cuidador.setNome(NOME);
        cuidador.setCpf(CPF);
        cuidador.setEmail(EMAIL);
        cuidador.setPhone(PHONE);
        cuidador.setSenha(SENHA);
        cuidador.addPerfil(PERFIL);
        cuidador.setSobre(SOBRE);
    }

    private void iniciaCuidadorDTO() {
        cuidadorDTO = new CuidadorDTO();

        cuidadorDTO.setId(ID);
        cuidadorDTO.setNome(NOME);
        cuidadorDTO.setCpf(CPF);
        cuidadorDTO.setEmail(EMAIL);
        cuidadorDTO.setPhone(PHONE);
        cuidadorDTO.setSenha(SENHA);
        cuidadorDTO.addPerfil(PERFIL);
        cuidadorDTO.setSobre(SOBRE);
    }

}