package com.loginscreen.service;

import com.loginscreen.model.dto.UserSearchDTO;
import com.loginscreen.model.dto.UserSaveDTO;
import com.loginscreen.model.dto.UserUpdateDTO;
import com.loginscreen.model.entity.User;
import com.loginscreen.model.mapper.UserMapper;
import com.loginscreen.repository.UserRepository;
import com.loginscreen.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public User insert(UserSaveDTO userSaveDTO){
        User userToSave = userMapper.toUser(userSaveDTO);
        return userRepository.save(userToSave);
    }

    public UserSearchDTO findById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        return userMapper.toDto(userOptional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado. Id: " + id + ", Tipo: " + User.class.getName()
        )));
    }

    public User update(UserUpdateDTO userUpdateDTO, Long id){
        UserSearchDTO userSearchDTO = findById(id);
        return userRepository.save(updateUser(userSearchDTO, userUpdateDTO));
    }

    public List<UserSearchDTO> findAll() {
        List<User> listUser = userRepository.findAll();
        return listUser.stream().map(userMapper::toDto).collect(Collectors.toList());
    }




    // TODO >> QUANDO CRIAR O AVATAR E/OU ALGUM OUTRO ATRIBUTO, INSERIR AQUI

    private User updateUser(UserSearchDTO userSearchDTO, UserUpdateDTO userUpdateDTO) {
        User userNew = userMapper.toUserFromSearch(userSearchDTO);
        userNew.setName(userUpdateDTO.getName());
        return userNew;
    }
}
