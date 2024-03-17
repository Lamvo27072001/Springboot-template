package com.ecommerce.website.controller;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.website.dto.AuthenticationRequest;
import com.ecommerce.website.dto.AuthenticationResponse;
import com.ecommerce.website.entities.User;
import com.ecommerce.website.repository.UserRepository;
import com.ecommerce.website.service.user.UserService;
import com.ecommerce.website.utils.JwtUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Solution 1
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest request,
            HttpServletResponse response)
            throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, JSONException,
            ServletException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "User is not activated");
            return null;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        User user = userRepository.findFirstByEmail(request.getUsername());

        final String jwt = jwtUtil.generateToken(request.getUsername());
        return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(jwt), HttpStatus.OK); // Solution 1
        // Solution 2: Dont need use builder annotation in AuthenticationResponse CLASS:
        // ==> return new AuthenticationResponse(jwt);

    }

    // Solution 2
    // @PostMapping("/authenticate")
    // public AuthenticationResponse createAuthenticationTokens(@RequestBody
    // AuthenticationRequest request,
    // HttpServletResponse response)
    // throws BadCredentialsException, DisabledException, UsernameNotFoundException,
    // IOException, JSONException,
    // ServletException {
    // try {
    // authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(request.getUsername(),
    // request.getPassword()));
    // } catch (BadCredentialsException e) {
    // throw new BadCredentialsException("Incorrect username or password");
    // } catch (DisabledException disabledException) {
    // response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "User is not
    // activated");
    // return null;
    // }
    // final UserDetails userDetails =
    // userDetailsService.loadUserByUsername(request.getUsername());
    // User user = userRepository.findFirstByEmail(request.getUsername());

    // final String jwt = jwtUtil.generateToken(request.getUsername());
    // return AuthenticationResponse.builder().token(jwt).build();
    // }
}
