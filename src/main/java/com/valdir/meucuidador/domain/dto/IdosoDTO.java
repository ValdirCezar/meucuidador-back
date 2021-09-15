package com.valdir.meucuidador.domain.dto;

import com.valdir.meucuidador.domain.Idoso;

import java.io.Serializable;

public class IdosoDTO extends UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public IdosoDTO(Idoso obj) {
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