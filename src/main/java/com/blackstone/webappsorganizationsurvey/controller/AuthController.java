package com.blackstone.webappsorganizationsurvey.controller;


import com.blackstone.webappsorganizationsurvey.config.MyUserDetailsService;
import com.blackstone.webappsorganizationsurvey.dto.AuthenticationRequest;
import com.blackstone.webappsorganizationsurvey.dto.AuthenticationResponse;
import com.blackstone.webappsorganizationsurvey.dto.UserRequest;
import com.blackstone.webappsorganizationsurvey.dto.UserResponse;
import com.blackstone.webappsorganizationsurvey.service.IUserService;
import com.blackstone.webappsorganizationsurvey.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/survey/form/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final MyUserDetailsService myUserDetailsService;

    private final JWTUtil jwtUtil;

    private final IUserService userService;

    /**
     * Login
     *
     * @param authenticationRequest authenticationRequest
     * @return Jwt token
     * @throws Exception when wrong username or password passed
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest authenticationRequest)
            throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()
                    , authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }


    @PostMapping("/register")
    public UserResponse register(@RequestBody @Valid UserRequest userRequest) {
        return this.userService.registerUser(userRequest);
    }
}
