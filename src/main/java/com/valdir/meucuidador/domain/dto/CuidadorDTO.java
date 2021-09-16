package com.valdir.meucuidador.domain.dto;

import com.valdir.meucuidador.domain.Cuidador;
import com.valdir.meucuidador.domain.enums.Perfil;

import java.io.Serializable;
import java.util.HashSet;

public class CuidadorDTO extends UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public CuidadorDTO(Cuidador obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.phone = obj.getPhone();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis();
        this.dataCriacao = obj.getDataCriacao();
    }

    public CuidadorDTO() {
        this.perfis = new HashSet<>();
        addPerfil(Perfil.CUIDADOR);
    }

    public void addPerfil(Perfil perfil) { perfis.add(perfil.getCodigo()); }
}
