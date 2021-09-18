package com.valdir.meucuidador.resources;

import com.valdir.meucuidador.domain.Cuidador;
import com.valdir.meucuidador.domain.dto.CuidadorDTO;
import com.valdir.meucuidador.domain.enums.Perfil;
import com.valdir.meucuidador.services.impl.CuidadorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class CuidadorResourceTest {

    private static final Integer ID                 = 1;
    private static final Integer ID_2               = 2;
    private static final String NOME                = "Valdir Cezar";
    private static final String CPF                 = "66090972088";
    private static final String EMAIL               = "email@test.com";
    private static final Perfil PERFIL              = Perfil.CUIDADOR;
    private static final Integer INDEX_0            = 0;
    private static final String SOBRE               = "Sobre";
    private static final String SENHA               = "123";
    private static final String PHONE               = "43984634308";
    private static final LocalDateTime DATA_CRIACAO = LocalDateTime.now();

    private Cuidador cuidador;
    private CuidadorDTO cuidadorDTO;

    @InjectMocks
    private CuidadorResource resource;

    @Mock
    private CuidadorServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        iniciaCuidador();
        iniciaCuidadorDTO();
    }

    @Test
    void findByIdSuccessTest() {
        when(service.findById(anyInt())).thenReturn(cuidador);

        ResponseEntity<CuidadorDTO> response = resource.findById(ID);

        Assertions.assertEquals(cuidadorDTO.getId(), response.getBody().getId());
        Assertions.assertEquals(cuidadorDTO.getNome(), response.getBody().getNome());
        Assertions.assertEquals(cuidadorDTO.getCpf(), response.getBody().getCpf());
        Assertions.assertEquals(cuidadorDTO.getEmail(), response.getBody().getEmail());
        Assertions.assertEquals(cuidadorDTO.getSenha(), response.getBody().getSenha());
        Assertions.assertEquals(cuidadorDTO.getPhone(), response.getBody().getPhone());
        Assertions.assertEquals(cuidadorDTO.getPerfis(), response.getBody().getPerfis());
        Assertions.assertEquals(cuidadorDTO.getDataCriacao(), response.getBody().getDataCriacao());
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
        cuidador.setDataCriacao(DATA_CRIACAO);
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
        cuidadorDTO.setDataCriacao(DATA_CRIACAO);
    }
}