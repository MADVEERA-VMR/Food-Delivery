package com.codedecode.userinfor.service;

import com.codedecode.userinfor.dto.UserDTO;
import com.codedecode.userinfor.entity.User;
import com.codedecode.userinfor.mapper.UserMapper;
import com.codedecode.userinfor.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<UserDTO> addUser(UserDTO userDTO) {

        User savedUser =  userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(savedUser), HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> fetchUserById(Integer id) {
        Optional<User> user = Optional.ofNullable(userRepo.findById(id).orElse(null));
        if (user.isPresent()) {
            return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(user.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        }
    }
}
