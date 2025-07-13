package com.mart.Agrimart.service;

import com.mart.Agrimart.dto.UserRegisterDto;
import com.mart.Agrimart.dto.UserResponse;
import com.mart.Agrimart.entity.Role;
import com.mart.Agrimart.entity.User;
import com.mart.Agrimart.exception.EmailAlreadyExistsException;
import com.mart.Agrimart.mapper.RegitserDtoToUser;
import com.mart.Agrimart.mapper.UserToUserResponse;
import com.mart.Agrimart.repository.RoleRepository;
import com.mart.Agrimart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> register(UserRegisterDto regitserDto) {

        if(userRepository.existsByEmail(regitserDto.getEmail()))
        {
            throw new EmailAlreadyExistsException("email already exists");
        }
        Set<Role> roles=regitserDto.getRoles().stream().
                        map(role-> roleRepository.findByRoleName(role)
                                .orElseThrow(()->new RuntimeException("role doesn't exist "+role)))
                                .collect(Collectors.toSet());
        regitserDto.setPassword(passwordEncoder.encode(regitserDto.getPassword()));
        User user= RegitserDtoToUser.RegisterDtoToUser(regitserDto);
        user.setRoles(roles);
        userRepository.save(user);
        UserResponse userResponse= UserToUserResponse.userToUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);


    }

}
