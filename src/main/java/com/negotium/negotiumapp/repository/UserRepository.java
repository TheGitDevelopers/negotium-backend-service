package com.negotium.negotiumapp.repository;

import com.negotium.negotiumapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);


}
