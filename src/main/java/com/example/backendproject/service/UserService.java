package com.example.backendproject.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backendproject.domain.User;

import java.util.Optional;

public interface UserService {
    User signUp(String username, String password);

    Optional<DecodedJWT> signIn(String username, String password);
}
