package com.example.backendproject.web;

import com.example.backendproject.service.UserService;
import com.example.backendproject.web.data.transfer.Credentials;
import com.example.backendproject.web.data.transfer.JwtToken;
import com.example.backendproject.web.data.transfer.JwtTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public void signUp(@RequestBody @Valid Credentials credentials) {
        userService.signUp(credentials.getUsername(), credentials.getPassword());
    }


    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public JwtToken signIn(@RequestBody @Valid Credentials credentials) {
        return userService.signIn(credentials.getUsername(), credentials.getPassword())
                .map(JwtTokenMapper.INSTANCE::toPayload)
                .orElseThrow(() -> new AccessDeniedException("Invalid username and/or password"));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
