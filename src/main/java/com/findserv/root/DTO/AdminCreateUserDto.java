package com.findserv.root.DTO;

import lombok.*;
import java.util.HashSet;
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

    // initialize so missing JSON yields an empty set (not null)
    private Set<String> roles = new HashSet<>();

    // defensive setter: ensures roles is never null
    public void setRoles(Set<String> roles) {
        this.roles = (roles == null) ? new HashSet<>() : roles;
    }
}
