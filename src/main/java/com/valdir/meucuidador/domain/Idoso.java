package com.valdir.meucuidador.domain;

import com.valdir.meucuidador.domain.dto.IdosoDTO;
import com.valdir.meucuidador.domain.enums.Perfil;

import javax.persistence.Entity;

@Entity
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

    public Idoso() {
        addPerfil(Perfil.IDOSO);
    }

    public void addPerfil(Perfil perfil) { perfis.add(perfil.getCodigo()); }

}