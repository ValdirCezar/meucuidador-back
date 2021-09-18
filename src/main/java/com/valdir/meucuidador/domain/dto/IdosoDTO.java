package com.valdir.meucuidador.domain.dto;

import com.valdir.meucuidador.domain.Idoso;
import com.valdir.meucuidador.domain.enums.Perfil;

import java.io.Serializable;
import java.util.HashSet;

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

    public IdosoDTO() {
        this.perfis = new HashSet<>();
        addPerfil(Perfil.IDOSO);
    }

    public void addPerfil(Perfil perfil) { perfis.add(perfil.getCodigo()); }
}