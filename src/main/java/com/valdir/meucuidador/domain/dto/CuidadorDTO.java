package com.valdir.meucuidador.domain.dto;

import com.valdir.meucuidador.domain.Cuidador;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
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
}
