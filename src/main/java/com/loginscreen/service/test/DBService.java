package com.loginscreen.service.test;

import com.loginscreen.model.dto.UserSaveDTO;
import com.loginscreen.model.entity.User;
import com.loginscreen.model.enumerator.Profile;
import com.loginscreen.model.mapper.UserMapper;
import com.loginscreen.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DBService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder pswEncoder;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public void instantiateDatabase() throws ParseException {

        User user1 = new User(null, "Usuário de Teste 01", "usuario_01@mail.com", pswEncoder.encode("123456"));
        User user2 = new User(null, "Usuário de Teste 02", "usuario_02@mail.com", pswEncoder.encode("123456"));
        User user3 = new User(null, "Usuário de Teste 03", "usuario_03@mail.com", pswEncoder.encode("123456"));
        User user4 = new User(null, "Usuário de Teste 04", "usuario_04@mail.com", pswEncoder.encode("123456"));


        UserSaveDTO user5DTO = new UserSaveDTO(null, "Usuário de Teste 05", "usuario_05@mail.com", pswEncoder.encode("123456"));
        UserSaveDTO user10DTO = new UserSaveDTO(null, "Adm de Teste 10", "adm_10@mail.com", pswEncoder.encode("123456"));

        User user5 = userMapper.toUser(user5DTO);
        User user10 = userMapper.toUser(user10DTO);


        user10.addProfile(Profile.ADMIN);

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user10));
    }
}
