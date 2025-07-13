package com.mart.Agrimart.mapper;

import com.mart.Agrimart.dto.LoginRequestDto;
import com.mart.Agrimart.dto.UserRegisterDto;
import com.mart.Agrimart.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegitserDtoToUser {

    public static User RegisterDtoToUser(UserRegisterDto registerDto)
    {
        User user=new User();
        user.setUserName(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setPhoneNo(registerDto.getPhoneNumber());
        user.setPassword(registerDto.getPassword());
        return user;
    }
}
