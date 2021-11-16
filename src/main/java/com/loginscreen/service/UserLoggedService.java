package com.loginscreen.service;

import com.loginscreen.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserLoggedService {

    public static UserSS authenticated(){
        try {
            // Retorna o usuário logado
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e) {
            return null;
        }
    }
}
