package com.findserv.root.DTO;


import com.findserv.root.entity.Role;
import com.findserv.root.entity.User;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private Set<String> roles; // multiple roles

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                        .map(Enum::name) // Role -> String
                        .collect(Collectors.toSet()))
                .build();
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setEmail(this.email);
        if (this.roles != null) {
            user.setRoles(this.roles.stream()
                    .map(Role::valueOf)
                    .collect(Collectors.toSet()));
        }
        return user;
    }


}
