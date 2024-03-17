package com.ecommerce.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.website.dto.ErrorResponse;
import com.ecommerce.website.dto.SignupDTO;
import com.ecommerce.website.dto.UserDTO;
import com.ecommerce.website.repository.UserRepository;
import com.ecommerce.website.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping
public class SignupController {
    @Autowired
    private UserService userService;
    private UserRepository userRepository;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {
        if (userService.hashUserWithEmail(signupDTO.getEmail())) {
            // Tạo một đối tượng chứa thông điệp và mã trạng thái HTTP
            // ErrorResponse errorResponse = new ErrorResponse("User not created, please
            // create again!",
            // HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(ErrorResponse.builder().message("User not created, please create again!")
                    .statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
        UserDTO createUser = userService.createUser(signupDTO);
        if (createUser == null) {
            return new ResponseEntity<>("User not created, please create again!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);

    }
}
