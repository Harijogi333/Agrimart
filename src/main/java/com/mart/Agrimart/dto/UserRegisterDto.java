package com.mart.Agrimart.dto;

import com.mart.Agrimart.entity.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @NotBlank(message = "userName is required")
    private String userName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min=6, message = "password should be atleast 6 characters")
    private String password;
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    private String phoneNumber;
    @NotEmpty(message = "atleast one role should be selected")
    private Set<String> roles;
}
