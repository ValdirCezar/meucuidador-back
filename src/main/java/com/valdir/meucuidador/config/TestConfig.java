package com.valdir.meucuidador.config;

import com.valdir.meucuidador.domain.Cuidador;
import com.valdir.meucuidador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private UsuarioRepository repository;

    @Bean
    public void startDB() {
        Cuidador c1 = new Cuidador();
        c1.setNome("Valdir Cezar");

        repository.saveAll(List.of(c1));
    }
}
