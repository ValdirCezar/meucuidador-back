package com.valdir.meucuidador.services.impl;

import com.valdir.meucuidador.domain.Cuidador;
import com.valdir.meucuidador.domain.dto.CuidadorDTO;
import com.valdir.meucuidador.domain.enums.Perfil;
import com.valdir.meucuidador.repository.CuidadorRepository;
import com.valdir.meucuidador.services.exception.DataIntegratyViolationException;
import com.valdir.meucuidador.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
        when(repository.findById(anyInt())).thenReturn(this.optionalCuidador);

        Cuidador response = service.findById(ID);

        if(optionalCuidador.isPresent()) {
            assertEquals(optionalCuidador.get().getId(), response.getId());
            assertEquals(optionalCuidador.get().getNome(), response.getNome());
            assertEquals(optionalCuidador.get().getCpf(), response.getCpf());
            assertEquals(optionalCuidador.get().getEmail(), response.getEmail());
            assertEquals(optionalCuidador.get().getPhone(), response.getPhone());
            assertEquals(optionalCuidador.get().getSenha(), response.getSenha());
            assertEquals(optionalCuidador.get().getSobre(), response.getSobre());
            assertEquals(optionalCuidador.get().getPerfis(), response.getPerfis());
        }
    }

    @Test
    void findByIdErrorTest() {
        ObjectNotFoundException notFoundException = new ObjectNotFoundException("Objeto não encontrado");
        when(repository.findById(anyInt())).thenThrow(notFoundException);

        try{
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(notFoundException.getClass(), ex.getClass());
            assertEquals(notFoundException.getMessage(), ex.getMessage());
        }
    }

    @Test
    void findAllTest() {
        List<Cuidador> list = List.of(cuidador);

        when(repository.findAll()).thenReturn(list);
        List<Cuidador> response = service.findAll();

        assertEquals(response.size(), list.size());
        assertEquals(response.get(INDEX_0).getId(), list.get(INDEX_0).getId());
        assertEquals(response.get(INDEX_0).getNome(), list.get(INDEX_0).getNome());
        assertEquals(response.get(INDEX_0).getCpf(), list.get(INDEX_0).getCpf());
        assertEquals(response.get(INDEX_0).getEmail(), list.get(INDEX_0).getEmail());
        assertEquals(response.get(INDEX_0).getPhone(), list.get(INDEX_0).getPhone());
        assertEquals(response.get(INDEX_0).getSenha(), list.get(INDEX_0).getSenha());
        assertEquals(response.get(INDEX_0).getSobre(), list.get(INDEX_0).getSobre());
        assertEquals(response.get(INDEX_0).getPerfis(), list.get(INDEX_0).getPerfis());
    }

    @Test
    void createSuccessTest() {
        when(repository.save(Mockito.any(Cuidador.class))).thenReturn(cuidador);

        Cuidador response = service.create(cuidadorDTO);

        assertEquals(cuidador.getId(), response.getId());
        assertEquals(cuidador.getNome(), response.getNome());
        assertEquals(cuidador.getCpf(), response.getCpf());
        assertEquals(cuidador.getEmail(), response.getEmail());
        assertEquals(cuidador.getPhone(), response.getPhone());
        assertEquals(cuidador.getSenha(), response.getSenha());
        assertEquals(cuidador.getSobre(), response.getSobre());
        assertEquals(cuidador.getPerfis(), response.getPerfis());
    }

    @Test
    void createWithCPFErrorTest() {
        DataIntegratyViolationException exception
                = new DataIntegratyViolationException("CPF " + JA_CADASTRADO_NO_SISTEMA);

        when(repository.findByCpf(Mockito.any())).thenReturn(optionalCuidador);

        try{
            cuidadorDTO.setId(ID_2);
            service.create(cuidadorDTO);
        } catch (Exception ex) {
            assertEquals(exception.getClass(), ex.getClass());
        }

    }

    @Test
    void createWithEmailErrorTest() {
        DataIntegratyViolationException exception
                = new DataIntegratyViolationException("E-MAIL " + JA_CADASTRADO_NO_SISTEMA);

        when(repository.findByEmail(Mockito.any())).thenReturn(optionalCuidador);

        try{
            cuidadorDTO.setId(ID_2);
            service.create(cuidadorDTO);
        } catch (Exception ex) {
            assertEquals(exception.getClass(), ex.getClass());
        }

    }

    @Test
    void createWithPhoneErrorTest() {

        when(repository.findByPhone(Mockito.any())).thenReturn(optionalCuidador);

        try{
            cuidadorDTO.setId(ID_2);
            service.create(cuidadorDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
        }

    }

    @Test
    void updateWithSuccessTest() {
        when(repository.findById(anyInt())).thenReturn(optionalCuidador);
        when(repository.save(Mockito.any())).thenReturn(cuidador);

        Cuidador response = service.update(cuidadorDTO, ID);

        assertEquals(cuidador.getId(), response.getId());
        assertEquals(cuidador.getNome(), response.getNome());
        assertEquals(cuidador.getCpf(), response.getCpf());
        assertEquals(cuidador.getEmail(), response.getEmail());
        assertEquals(cuidador.getPhone(), response.getPhone());
        assertEquals(cuidador.getSenha(), response.getSenha());
        assertEquals(cuidador.getSobre(), response.getSobre());
        assertEquals(cuidador.getPerfis(), response.getPerfis());
    }

    @Test
    void updateWithObjectNotFoundErrorTest() {
        try {
            service.update(cuidadorDTO, ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void deleteByIdSuccessTest() {
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
    }

    @Test
    void equalsAndHashcode() {
        cuidador.equals(new Cuidador());
        cuidadorDTO.equals(new CuidadorDTO());

        assertEquals(cuidador.hashCode(), cuidador.hashCode());
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