package com.example.userManagement.controller;

import com.example.userManagement.config.JwtGenerator;
import com.example.userManagement.exception.LoginErrorException;
import com.example.userManagement.service.UserService;
import com.example.userManagement.service.dto.AuthResponseDto;
import com.example.userManagement.service.dto.LoginRequestDto;
import com.example.userManagement.service.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth/")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUsername(),
                            loginRequestDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);

            return ResponseEntity.ok(new AuthResponseDto(token));
        } catch (AuthenticationException e) {
            throw new LoginErrorException("Incorrect credential", e);
        }
    }

    @PostMapping("create")
    public ResponseEntity<Void> addUser(@RequestBody UserRequestDto userRequestDto) {
        userService.addUser(userRequestDto);
        return ResponseEntity.ok().build();
    }

}
