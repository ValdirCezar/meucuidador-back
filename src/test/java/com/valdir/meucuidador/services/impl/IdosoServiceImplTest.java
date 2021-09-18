package com.valdir.meucuidador.services.impl;

import com.valdir.meucuidador.domain.Idoso;
import com.valdir.meucuidador.domain.dto.IdosoDTO;
import com.valdir.meucuidador.domain.enums.Perfil;
import com.valdir.meucuidador.repository.IdosoRepository;
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
class IdosoServiceImplTest {

    private static final Integer ID        = 1;
    private static final Integer ID_2      = 2;
    private static final String NOME       = "Valdir Cezar";
    private static final String CPF        = "66090972088";
    private static final String EMAIL      = "email@test.com";
    private static final Perfil PERFIL     = Perfil.IDOSO;
    private static final Integer INDEX_0   = 0;
    private static final String SOBRE      = "Sobre";
    private static final String SENHA      = "123";
    private static final String PHONE      = "43984634308";

    private static final String JA_CADASTRADO_NO_SISTEMA = "já cadastrado no sistema";

    @InjectMocks
    private IdosoServiceImpl service;

    @Mock
    private IdosoRepository repository;

    private Optional<Idoso> optionalIdoso;
    private Idoso idoso;
    private IdosoDTO idosoDTO;

    @BeforeEach
    void setUp() {
        iniciaOptionalIdoso();
        iniciaIdoso();
        iniciaIdosoDTO();
    }

    @Test
    void findByIdSuccessTest() {
        when(repository.findById(anyInt())).thenReturn(this.optionalIdoso);

        Idoso response = service.findById(ID);

        if(optionalIdoso.isPresent()) {
            assertEquals(optionalIdoso.get().getId(), response.getId());
            assertEquals(optionalIdoso.get().getNome(), response.getNome());
            assertEquals(optionalIdoso.get().getCpf(), response.getCpf());
            assertEquals(optionalIdoso.get().getEmail(), response.getEmail());
            assertEquals(optionalIdoso.get().getPhone(), response.getPhone());
            assertEquals(optionalIdoso.get().getSenha(), response.getSenha());
            assertEquals(optionalIdoso.get().getSobre(), response.getSobre());
            assertEquals(optionalIdoso.get().getPerfis(), response.getPerfis());
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
        List<Idoso> list = List.of(idoso);

        when(repository.findAll()).thenReturn(list);
        List<Idoso> response = service.findAll();

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
        when(repository.save(Mockito.any(Idoso.class))).thenReturn(idoso);

        Idoso response = service.create(idosoDTO);

        assertEquals(idoso.getId(), response.getId());
        assertEquals(idoso.getNome(), response.getNome());
        assertEquals(idoso.getCpf(), response.getCpf());
        assertEquals(idoso.getEmail(), response.getEmail());
        assertEquals(idoso.getPhone(), response.getPhone());
        assertEquals(idoso.getSenha(), response.getSenha());
        assertEquals(idoso.getSobre(), response.getSobre());
        assertEquals(idoso.getPerfis(), response.getPerfis());
        assertEquals("IDOSO", PERFIL.getDescricao());
    }

    @Test
    void createWithCPFErrorTest() {
        DataIntegratyViolationException exception
                = new DataIntegratyViolationException("CPF " + JA_CADASTRADO_NO_SISTEMA);

        when(repository.findByCpf(Mockito.any())).thenReturn(optionalIdoso);

        try{
            idosoDTO.setId(ID_2);
            service.create(idosoDTO);
        } catch (Exception ex) {
            assertEquals(exception.getClass(), ex.getClass());
        }

    }

    @Test
    void createWithEmailErrorTest() {
        DataIntegratyViolationException exception
                = new DataIntegratyViolationException("E-MAIL " + JA_CADASTRADO_NO_SISTEMA);

        when(repository.findByEmail(Mockito.any())).thenReturn(optionalIdoso);

        try{
            idosoDTO.setId(ID_2);
            service.create(idosoDTO);
        } catch (Exception ex) {
            assertEquals(exception.getClass(), ex.getClass());
        }

    }

    @Test
    void createWithPhoneErrorTest() {

        when(repository.findByPhone(Mockito.any())).thenReturn(optionalIdoso);

        try{
            idosoDTO.setId(ID_2);
            service.create(idosoDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
        }

    }

    @Test
    void updateWithSuccessTest() {
        when(repository.findById(anyInt())).thenReturn(optionalIdoso);
        when(repository.save(Mockito.any())).thenReturn(idoso);

        Idoso response = service.update(idosoDTO, ID);

        assertEquals(idoso.getId(), response.getId());
        assertEquals(idoso.getNome(), response.getNome());
        assertEquals(idoso.getCpf(), response.getCpf());
        assertEquals(idoso.getEmail(), response.getEmail());
        assertEquals(idoso.getPhone(), response.getPhone());
        assertEquals(idoso.getSenha(), response.getSenha());
        assertEquals(idoso.getSobre(), response.getSobre());
        assertEquals(idoso.getPerfis(), response.getPerfis());
    }

    @Test
    void updateWithObjectNotFoundErrorTest() {
        try {
            service.update(idosoDTO, ID);
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

    private void iniciaOptionalIdoso() {
        optionalIdoso = Optional.of(new Idoso());

        optionalIdoso.get().setId(ID);
        optionalIdoso.get().setNome(NOME);
        optionalIdoso.get().setCpf(CPF);
        optionalIdoso.get().setEmail(EMAIL);
        optionalIdoso.get().setPhone(PHONE);
        optionalIdoso.get().setSenha(SENHA);
        optionalIdoso.get().addPerfil(PERFIL);
        optionalIdoso.get().setSobre(SOBRE);
    }

    private void iniciaIdoso() {
        idoso = new Idoso();
        idoso.setId(ID);
        idoso.setNome(NOME);
        idoso.setCpf(CPF);
        idoso.setEmail(EMAIL);
        idoso.setPhone(PHONE);
        idoso.setSenha(SENHA);
        idoso.addPerfil(PERFIL);
        idoso.setSobre(SOBRE);
    }

    private void iniciaIdosoDTO() {
        idosoDTO = new IdosoDTO();
        idosoDTO = new IdosoDTO(idoso);
    }

}