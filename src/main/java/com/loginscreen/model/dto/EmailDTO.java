package com.loginscreen.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class EmailDTO {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Este campo deve ser preenchido.")
    @Email(message = "Email inválido.")
    private String email;

}
