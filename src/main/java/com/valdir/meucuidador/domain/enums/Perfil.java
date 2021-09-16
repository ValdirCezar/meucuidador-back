package com.valdir.meucuidador.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum Perfil {

    ADMIN(0, "ROLE_ADMIN"), CUIDADOR(1, "CUIDADOR"), IDOSO(2, "IDOSO");

    private Integer codigo;
    private String descricao;

    public static Perfil toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(Perfil x : Perfil.values()) {
            if(Objects.equals(cod, x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código de perfil inválido");
    }
}
