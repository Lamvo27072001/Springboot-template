package com.ecommerce.website.dto;

import com.ecommerce.website.enums.UserRole;

import lombok.Data;

@Data
public class UserDTO {
    public UserDTO(long id, String name, String email, UserRole userRole) {

    }

    private long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;

}
