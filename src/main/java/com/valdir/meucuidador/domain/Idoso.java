package com.valdir.meucuidador.domain;

import com.valdir.meucuidador.domain.dto.IdosoDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Idoso extends Usuario{

    public Idoso(IdosoDTO obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.phone = obj.getPhone();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis();
        this.dataCriacao = obj.getDataCriacao();
    }
}