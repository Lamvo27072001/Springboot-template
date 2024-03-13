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
        // Add user thorugh Class User
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setUserRole(UserRole.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        User creatUser = userRepository.save(user);

        // UserDTO is a object that mapping from User class, because there are some
        // field u dont want to response, so use UserDTO instead of User class
        UserDTO userDTO = new UserDTO();
        userDTO.setId(creatUser.getId());
        userDTO.setEmail(creatUser.getEmail());
        userDTO.setName(creatUser.getName());
        userDTO.setUserRole(creatUser.getUserRole());
        return userDTO;
    }

    @Override
    public boolean hashUserWithEmail(String email) {

        return userRepository.findFirstByEmail(email) != null;
    }
}
