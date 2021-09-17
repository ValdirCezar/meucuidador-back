package com.valdir.meucuidador.domain;

import com.valdir.meucuidador.domain.dto.CuidadorDTO;
import com.valdir.meucuidador.domain.enums.Perfil;

import javax.persistence.Entity;

@Entity
public class Cuidador extends Usuario {

    public Cuidador(CuidadorDTO obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.phone = obj.getPhone();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis();
        this.sobre = obj.getSobre();
        this.dataCriacao = obj.getDataCriacao();
    }

    public Cuidador() {
        addPerfil(Perfil.CUIDADOR);
    }

    public void addPerfil(Perfil perfil) { perfis.add(perfil.getCodigo()); }

}
