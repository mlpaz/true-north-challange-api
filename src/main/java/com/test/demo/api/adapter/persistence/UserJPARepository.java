package com.test.demo.api.adapter.persistence;

import com.test.demo.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserJPARepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByEmail(String email);

    Optional<User> findFirstByEmailAndPassword(String email, String password);

}
