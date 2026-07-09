package com.womensafety.repository;

import com.womensafety.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * REPOSITORY LAYER
 * -----------------
 * A Repository's only job is talking to the database - no business logic here.
 * Spring Data JPA is almost magic: just by naming a method correctly
 * (e.g. findByEmail), it writes the SQL query for us behind the scenes.
 * We never write raw SQL for basic operations like this.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
