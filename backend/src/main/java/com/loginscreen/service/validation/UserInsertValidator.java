package com.loginscreen.service.validation;

import com.loginscreen.controller.exception.FieldMessage;
import com.loginscreen.model.dto.UserSaveDTO;
import com.loginscreen.model.entity.User;
import com.loginscreen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;


public class UserInsertValidator implements ConstraintValidator<UserInsert, UserSaveDTO> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void initialize(UserInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserSaveDTO userSaveDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        User aux = userRepository.findByEmail(userSaveDTO.getEmail());
        if(aux != null){
            list.add(new FieldMessage("email", "Já existe um usuário com este email."));
        }

        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();

    }
}
