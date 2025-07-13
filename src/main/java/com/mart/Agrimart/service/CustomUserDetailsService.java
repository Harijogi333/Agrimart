package com.mart.Agrimart.service;

import com.mart.Agrimart.entity.User;
import com.mart.Agrimart.repository.UserRepository;
import com.mart.Agrimart.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByEmail(username)
                .orElseThrow(()-> new RuntimeException("user not found with this "+username));

        return new CustomUserDetails(user);
    }
}
