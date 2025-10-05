package com.findserv.root.controller;

import com.findserv.root.DTO.LoginDTO;
import com.findserv.root.entity.User;
import com.findserv.root.repos.UserRepository;
import com.findserv.root.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class    AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginDTO dto) {
        User user = userRepo.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        var roles = user.getRoles().stream().map(Enum::name).collect(Collectors.toList());
        String token = jwtUtil.generateToken(user.getUsername(), roles);

        return new Object() {
            public final String jwt = token;
            public final String username = user.getUsername();
            public final Object role = roles;
        };
    }
}
