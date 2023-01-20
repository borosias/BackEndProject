package com.example.backendproject.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backendproject.data.RoleRepository;
import com.example.backendproject.data.UserRepository;
import com.example.backendproject.domain.Role;
import com.example.backendproject.domain.User;
import com.example.backendproject.exception.RoleNotFoundException;
import com.example.backendproject.exception.UserAlreadyExistsException;
import com.example.backendproject.exception.UserNotFoundException;
import com.example.backendproject.security.JwtProvider;
import com.example.backendproject.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtProvider jwtProvider;

    @Transactional
    @Override
    public User signUp(String username, String password) {
        if (userRepository.existsByUsername(username))
            throw new UserAlreadyExistsException("User with username %s already exists".formatted(username));
        var user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        var userRole = roleRepository.findByName(Role.USER)
                .orElseThrow(() -> new RoleNotFoundException("User with username %s already exists".formatted(Role.USER)));
        user.setRole(userRole);
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public Optional<DecodedJWT> signIn(String username, String password) {
        return jwtProvider
                .toDecodedJWT(jwtProvider
                        .generateToken(getByCredentials(username, password)
                                .getUsername()));
    }

    private Optional<User> findByCredentials(String username, String password) {
        var optUser = userRepository.findByUsername(username);
        if (optUser.isPresent()) {
            var user = optUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return optUser;
            }
        }
        return Optional.empty();
    }

    private User getByCredentials(String username, String password) {
        return findByCredentials(username, password)
                .orElseThrow(() -> new UserNotFoundException("User with username %s not found".formatted(username)));
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }
}
