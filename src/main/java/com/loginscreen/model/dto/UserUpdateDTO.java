package com.loginscreen.model.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserUpdateDTO {

    @NotEmpty(message = "Este campo deve ser preenchido.")
    @Size(min = 3, max = 100, message = "O nome deve conter entre 3 e 100 letras.")
    private String name;

}
