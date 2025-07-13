package com.mart.Agrimart.mapper;

import com.mart.Agrimart.dto.UserResponse;
import com.mart.Agrimart.entity.User;

public class UserToUserResponse {

    public static UserResponse userToUserResponse(User user)
    {
        UserResponse userResponse=new UserResponse();
        userResponse.setUserName(user.getUserName());
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNo());
        userResponse.setRoles(user.getRoles());
        return userResponse;

    }
}
