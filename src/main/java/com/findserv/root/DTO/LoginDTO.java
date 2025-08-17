package com.findserv.root.DTO;

import lombok.*;

@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LoginDTO {
    private String username;
    private String password;
}