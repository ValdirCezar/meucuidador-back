package com.valdir.meucuidador.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Perfil {

    ADMIN(0, "ROLE_ADMIN"), CUIDADOR(1, "CUIDADOR"), IDOSO(2, "IDOSO");

    private Integer codigo;
    private String descricao;

}
