package com.ecommerce.foodordering.controller;

import com.ecommerce.foodordering.core.utils.JWTUtil;
import com.ecommerce.foodordering.dtos.requests.user.AuthenticationRequest;
import com.ecommerce.foodordering.dtos.requests.user.CreateUserRequest;
import com.ecommerce.foodordering.dtos.responses.user.AuthenticationResponse;
import com.ecommerce.foodordering.dtos.responses.user.GetAllUserResponse;
import com.ecommerce.foodordering.entities.User;
import com.ecommerce.foodordering.repository.UserRepository;
import com.ecommerce.foodordering.services.abstracts.AuthService;
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

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, UserRepository userRepository, UserDetailsService userDetailsService, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody CreateUserRequest createUserRequest){
        GetAllUserResponse getAllUserResponse = authService.createUser(createUserRequest);
        if(getAllUserResponse == null) return new ResponseEntity<>
                ("Customer is not created. Try again later.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(getAllUserResponse,HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()));
        }catch (BadCredentialsException exception){
            throw new BadCredentialsException("Incorrect email or password");
        }
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        Optional<User> optionalUser = this.userRepository.findFirstByEmail(userDetails.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(String.valueOf((optionalUser.get().getUserRole())));
        }
        return authenticationResponse;
    }

}
