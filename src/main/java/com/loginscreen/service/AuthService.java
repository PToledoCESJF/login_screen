package com.loginscreen.service;

import com.loginscreen.model.entity.User;
import com.loginscreen.repository.UserRepository;
import com.loginscreen.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {
    
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private iEmailService emailService;

    private static final Random random = new Random();
    
    public void sendNewPassword(String email) {
        
        User user = userRepository.findByEmail(email);
        
        if(user == null) {
            throw new ObjectNotFoundException("Email não encontrado.");
        }
        
        String newPass = newPassword();
        user.setPassword(passwordEncoder.encode((newPass)));
        
        userRepository.save(user);
        emailService.sendNewPasswordEmail(user, newPass);
        
    }

    private String newPassword() {
        char[] vet = new char[10];
        for(int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if(opt == 0) { // gera um digito
            return (char) (random.nextInt(10) + 48);
        }else if(opt == 1) { // gera letra maiuscula
            return (char) (random.nextInt(26) + 65);
        }else  { // gera letra minuscula
            return (char) (random.nextInt(26) + 97);
        }
    }
}
