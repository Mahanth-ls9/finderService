package com.findserv.root.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;  // Will be stored in encrypted form (BCrypt)

    @Column(nullable = false)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles; // e.g., ADMIN, USER
}
