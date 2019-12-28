package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.exception.DuplicateEmailException;
import com.negotium.negotiumapp.exception.DuplicateUsernameException;
import com.negotium.negotiumapp.exception.InvalidLoginException;
import com.negotium.negotiumapp.model.user.User;
import com.negotium.negotiumapp.model.user.UserDto;
import com.negotium.negotiumapp.model.user.UserMapper;
import com.negotium.negotiumapp.model.user.UserRole;
import com.negotium.negotiumapp.repository.UserRepository;
import com.negotium.negotiumapp.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final String DEFAULT_ROLE = "ROLE_USER";

    private UserRepository userRepository;

    private UserRoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean addWithDefaultRole(UserDto user) {
        UserRole defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        try {
            save(user);
            return true;
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
            errors.forEach(err -> System.err.println(
                    err.getPropertyPath() + " " +
                            err.getInvalidValue() + " " +
                            err.getMessage()));
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void loginUser(String username, String password) {
        JwtUserDetailsService jwtUserDetailsService = new JwtUserDetailsService(userRepository, passwordEncoder);
        User user = userRepository.findByUsername(username);
        boolean isPasswordOk = passwordEncoder.matches(password, user.getPassword());
        if (isPasswordOk) {
            jwtUserDetailsService.loadUserByUsername(username);
        } else {
            throw new InvalidLoginException();
        }
    }

    private UserDto save(UserDto user) {
        Optional<User> userByUsername = userRepository.findAllByUsername(user.getUsername());
        userByUsername.ifPresent(x -> {
            throw new DuplicateUsernameException();
        });
        userByUsername.ifPresent(x -> {
            throw new DuplicateEmailException();
        });
        User userEntity = UserMapper.toEntity(user);
        User savedUser = userRepository.save(userEntity);
        return UserMapper.toDto(savedUser);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}


