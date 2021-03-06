package com.loginscreen.model.dto;

import com.loginscreen.service.validation.UserInsert;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@UserInsert
public class UserSaveDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;

    @NotEmpty(message = "Este campo deve ser preenchido.")
    @Size(min = 3, max = 100, message = "O nome deve conter entre 3 e 100 letras.")
    private String name;

    @NotEmpty(message = "Este campo deve ser preenchido.")
    @Email(message = "Email inválido.")
    private String email;

    @NotEmpty(message = "Este campo deve ser preenchido.")
    @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres.")
    private String password;

}
