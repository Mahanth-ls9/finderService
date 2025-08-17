package com.findserv.root.DTO;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCreateUserDto {
    private String username;
    private String email;
    private String password;
    private Set<String> roles; // ADMIN decides roles
}

