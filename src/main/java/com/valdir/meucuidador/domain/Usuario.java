package com.valdir.meucuidador.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_USUARIO")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String nome;

    @Column(unique = true)
    protected String cpf;

    @Column(unique = true)
    protected String phone;

    @Column(unique = true)
    protected String email;
    protected String senha;
    protected String sobre;

    @CollectionTable
    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<Integer> perfis;
    protected LocalDateTime dataCriacao = LocalDateTime.now();

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
