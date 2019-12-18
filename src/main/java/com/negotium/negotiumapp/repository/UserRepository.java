package com.negotium.negotiumapp.repository;

import com.negotium.negotiumapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);


}
