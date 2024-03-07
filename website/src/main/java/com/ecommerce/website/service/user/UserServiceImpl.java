package com.ecommerce.website.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ecommerce.website.dto.SignupDTO;
import com.ecommerce.website.dto.UserDTO;
import com.ecommerce.website.entities.User;
import com.ecommerce.website.enums.UserRole;
import com.ecommerce.website.repository.UserRepository;

@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setUserRole(UserRole.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        userRepository.save(user);
        return user.mapUsertoUserDTO();
    }
}
