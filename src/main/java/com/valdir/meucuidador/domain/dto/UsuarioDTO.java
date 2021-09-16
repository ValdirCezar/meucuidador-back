package com.valdir.meucuidador.domain.dto;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class UsuarioDTO {

    protected Integer id;

    @NotEmpty(message = "Campo NOME é requerido")
    @Size(min = 3, max = 99, message = "O campo NOME deve ter entre 3 e 99 caracteres")
    protected String nome;

    @NotEmpty(message = "Campo CPF é requerido")
    @CPF
    protected String cpf;

    @NotEmpty(message = "Campo PHONE é requerido")
    protected String phone;

    @NotEmpty(message = "Campo E-MAIL é requerido")
    @Email
    protected String email;

    @NotEmpty(message = "Campo SENHA é requerido")
    @Size(min = 3, max = 99, message = "O campo SENHA deve ter entre 3 e 99 caracteres")
    protected String senha;

    @Size(max = 3000, message = "O campo SOBRE pode ter entre 0 e 3000 caracteres")
    protected String sobre;

    protected Set<Integer> perfis;
    protected LocalDateTime dataCriacao;

}
