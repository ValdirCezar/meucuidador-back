package com.valdir.meucuidador.resources.exceptions;

import com.valdir.meucuidador.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ResourceExceptionHandlerTest {

    private static final String NÃO_ENCONTRADO = "Objeto não encontrado";
    private static final String NAO_ENCONTRADO = NÃO_ENCONTRADO;
    private static final LocalDateTime NOW = LocalDateTime.now();
    private static final String PATH = "/path/id";
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void objectNotFoundExceptionTest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFoundException(new ObjectNotFoundException(NAO_ENCONTRADO), request);

        StandardError standardError =
                new StandardError(NOW, HttpStatus.NOT_FOUND.value(), NAO_ENCONTRADO, NAO_ENCONTRADO, PATH);

        assertEquals(standardError.getClass(), response.getBody().getClass());
        assertEquals(standardError.getMessage(), response.getBody().getMessage());
        assertEquals(standardError.getStatus(), response.getStatusCode().value());
        assertEquals(NAO_ENCONTRADO, standardError.getTrace());
        assertEquals(NOW, standardError.getTimestamp());
        assertEquals(PATH, standardError.getPath());
    }
}