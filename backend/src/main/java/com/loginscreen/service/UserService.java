package com.loginscreen.service;

import com.loginscreen.model.dto.UserSearchDTO;
import com.loginscreen.model.dto.UserSaveDTO;
import com.loginscreen.model.dto.UserUpdateDTO;
import com.loginscreen.model.entity.User;
import com.loginscreen.model.enumerator.Profile;
import com.loginscreen.model.mapper.UserMapper;
import com.loginscreen.repository.UserRepository;
import com.loginscreen.security.UserSS;
import com.loginscreen.service.exception.AuthorizationException;
import com.loginscreen.service.exception.DataIntegrityException;
import com.loginscreen.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private iEmailService emailService;
    private final UserMapper userMapper = UserMapper.INSTANCE;


    public User insert(UserSaveDTO userSaveDTO){
        userSaveDTO.setPassword(passwordEncoder.encode(userSaveDTO.getPassword()));
        User userToSave = userMapper.toUser(userSaveDTO);
        return userRepository.save(userToSave);
    }

    public UserSearchDTO findById(Long id){

        UserSS userSS = UserLoggedService.authenticated();
        if (userSS == null || !userSS.hasRole(Profile.ADMIN) && !id.equals(userSS.getId())){
            throw new AuthorizationException("Acesso negado.");
        }

        Optional<User> userOptional = userRepository.findById(id);
        return userMapper.toDto(userOptional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado. Id: " + id + ", Tipo: " + User.class.getName()
        )));
    }

    public User update(UserUpdateDTO userUpdateDTO, Long id){
        UserSearchDTO userSearchDTO = findById(id);
        return userRepository.save(updateUser(userSearchDTO, userUpdateDTO));
    }

    public Page<UserSearchDTO> findAll(Pageable pageable) {
        Page<User> listUser = userRepository.findAll(pageable);
        return listUser.map(userMapper::toDto);
    }

    public void delete(Long id) {
        UserSearchDTO userSearchDTO = findById(id);
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityException e){
            throw new DataIntegrityException("Não é permitido excluir usuários que possuem dados relacionados com outras tabelas");
        }
    }


    private User updateUser(UserSearchDTO userSearchDTO, UserUpdateDTO userUpdateDTO) {
        User userNew = userMapper.toUserFromSearch(userSearchDTO);
        userNew.setName(userUpdateDTO.getName());
        userNew.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        return userNew;
    }
}
