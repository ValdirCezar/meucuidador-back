package com.valdir.meucuidador.domain;

import com.valdir.meucuidador.domain.enums.Perfil;

import javax.persistence.Entity;

@Entity
public class Idoso extends Usuario{

    public void addPerfil(Perfil perfil) {
        this.perfis.add(Perfil.IDOSO.getCodigo());
    }
}