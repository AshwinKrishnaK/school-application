package com.threadsafe.school.repo;

import com.threadsafe.school.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for User entity operations.
 * Extends JpaRepository to provide CRUD functionality for User entity.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email the email of the user to be found
     * @return an Optional containing the user if found, otherwise empty
     */
    Optional<User> findByEmail(String email);
}
