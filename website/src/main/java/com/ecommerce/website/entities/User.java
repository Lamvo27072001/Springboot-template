package com.ecommerce.website.entities;

import com.ecommerce.website.dto.UserDTO;
import com.ecommerce.website.enums.UserRole;

import ch.qos.logback.core.subst.Token.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    private String email;

    private String password;

    // @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private byte[] img;

}
