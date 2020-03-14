package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.exception.*;
import com.negotium.negotiumapp.model.user.User;
import com.negotium.negotiumapp.model.user.UserDto;
import com.negotium.negotiumapp.model.user.UserMapper;
import com.negotium.negotiumapp.model.user.UserRole;
import com.negotium.negotiumapp.repository.UserRepository;
import com.negotium.negotiumapp.repository.UserRoleRepository;
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

    private UserDto save(UserDto user) {
        List<User> userByUsername = userRepository.findByUsernameContaining(user.getUsername());
        for (User e : userByUsername) {
            if (e.getUsername().equals(user.getUsername())) {
                throw new DuplicateUsernameException("User with this username is already exists");
            }
        }
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        userByEmail.ifPresent(x -> {
            throw new DuplicateEmailException("User with this email address is already exists");
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

    public List<UserDto> findAllByUsername(String username) {
        return userRepository.findByUsernameContaining(username)
                .stream()
                .filter(x -> x.getUsername().contains(username))
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDto);
    }

    public Boolean deleteById(Long id) {
        Boolean isUserDelete;
        Optional<User> userToDelete = userRepository.findById(id);
        userToDelete.ifPresentOrElse(x ->
                        userRepository.deleteById(id),
        () -> {
            throw new UserNotFoundException("User not found");
        });
        isUserDelete = true;
        return isUserDelete;
    }
}