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

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> register(UserRegisterDto regitserDto) {

        if(userRepository.existsByEmail(regitserDto.getEmail()))
        {
            throw new EmailAlreadyExistsException("email already exists");
        }
        Set<Role> roles=regitserDto.getRoles().stream().
                        map(role-> roleRepository.findByRoleName(role.toUpperCase())
                                .orElseThrow(()->new RuntimeException("role doesn't exist "+role)))
                                .collect(Collectors.toSet());
        regitserDto.setPassword(passwordEncoder.encode(regitserDto.getPassword()));
        User user= RegitserDtoToUser.RegisterDtoToUser(regitserDto);
        user.setRoles(roles);
        user.setIsVerified(false);
        user.setVerificationToken(UUID.randomUUID().toString());
        user.setVerificationTokenExpiry(LocalDateTime.now().plusHours(24));
        emailService.sendVerificationEmail(user.getEmail(),"http://localhost:8080/verify?token="+user.getVerificationToken());
        userRepository.save(user);
        return ResponseEntity.ok().body(Map.of("message","verify your mail by clicking on the link."));
    }

    public ResponseEntity<?> verifyEmail(String token) {

        User user=userRepository.findByVerificationToken(token)
                .orElseThrow(()->new RuntimeException("invalid token"));
        if(user.getVerificationTokenExpiry().isBefore(LocalDateTime.now()))
        {
            throw new RuntimeException("token expired,Register again");
        }
        user.setIsVerified(true);
        user.setVerificationToken(null);
        user.setVerificationTokenExpiry(null);
        userRepository.save(user);

        return ResponseEntity.ok().body(Map.of("message","mail veriofication done.login now"));

    }
}
