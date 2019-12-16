package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.domain.User;
import com.negotium.negotiumapp.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Set;

@Service
public class UserService{

   private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
            errors.forEach(err -> System.err.print(
                    err.getPropertyPath() + " " + err.getInvalidValue() + " " +
                            err.getMessage()));
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String loginUser (User user){
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles","user")
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 600000)) //Token expiration after 10min
                .signWith(SignatureAlgorithm.HS256, user.getPassword())
                .compact();
    }
}



