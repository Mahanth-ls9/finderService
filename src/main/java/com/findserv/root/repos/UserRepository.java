package com.findserv.root.repos;

import com.findserv.root.entity.Role;
import com.findserv.root.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByRolesIn(Set<Role> roles);

    boolean existsByUsername(String username);
}

