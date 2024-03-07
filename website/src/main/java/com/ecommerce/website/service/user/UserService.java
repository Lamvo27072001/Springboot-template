package com.ecommerce.website.service.user;

import com.ecommerce.website.dto.SignupDTO;
import com.ecommerce.website.dto.UserDTO;

public interface UserService {

    UserDTO createUser(SignupDTO signupDTO);

}
