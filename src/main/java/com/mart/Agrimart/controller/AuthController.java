package com.mart.Agrimart.controller;

import com.mart.Agrimart.dto.LoginRequestDto;
import com.mart.Agrimart.dto.UserResponse;
import com.mart.Agrimart.mapper.UserToUserResponse;
import com.mart.Agrimart.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request)
    {

        try
        {
            Authentication auth=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),requestDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);

            CustomUserDetails userDetails=(CustomUserDetails) auth.getPrincipal();
            HttpSession session=request.getSession(true);
            session.setAttribute("userId",userDetails.getUser().getId());
            session.setAttribute(
                    org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
            );
            UserResponse userResponse= UserToUserResponse.userToUserResponse(userDetails.getUser());

            return ResponseEntity.ok(userResponse);


        }
        catch(BadCredentialsException e)
        {
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password"));
        }
    }
}
