package com.guilherme.delfino.pubsub.token.repository;

import com.guilherme.delfino.pubsub.token.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserApplicationRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);
}
