package com.valdir.meucuidador.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_USUARIO")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String phone;
    private String email;
    private String senha;
    private String sobre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) && cpf.equals(usuario.cpf) && phone.equals(usuario.phone) && email.equals(usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, phone, email);
    }
}
