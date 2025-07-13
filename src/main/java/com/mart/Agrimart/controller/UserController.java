package com.mart.Agrimart.controller;

import com.mart.Agrimart.dto.UserRegisterDto;
import com.mart.Agrimart.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDto registerDto )
    {
        return userService.register(registerDto);
    }
    @PostMapping("/api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request)
    {
        System.out.println("came here ................................................>");
        HttpSession session=request.getSession(false);
        if(session!=null)
        {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }
}
