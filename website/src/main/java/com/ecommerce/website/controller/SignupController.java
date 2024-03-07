package com.ecommerce.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.website.dto.SignupDTO;
import com.ecommerce.website.dto.UserDTO;
import com.ecommerce.website.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class SignupController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {
        UserDTO createUser = userService.createUser(signupDTO);
        if (createUser == null) {
            return new ResponseEntity<>("User not created, please create again!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);

    }
}
