package com.findserv.root.service;

import com.findserv.root.DTO.AdminCreateUserDto;
import com.findserv.root.DTO.UserDto;
import com.findserv.root.DTO.UserRegistrationDto;
import com.findserv.root.config.SecurityConfig;
import com.findserv.root.entity.Role;
import com.findserv.root.entity.User;
import com.findserv.root.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service

public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserDto.fromEntity(user);
    }

    public UserDto registerUser(UserRegistrationDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword()) // ⚠️ later hash with BCrypt
                .roles(Set.of(Role.USER))
                .build();

        return UserDto.fromEntity(userRepository.save(user));
    }

    public UserDto createUserByAdmin(AdminCreateUserDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(dto.getRoles().stream()
                        .map(Role::valueOf) // convert String -> Enum
                        .collect(Collectors.toSet()))
                .build();

        return UserDto.fromEntity(userRepository.save(user));
    }


    public List<UserDto> getUsersByRole(String roleName) {
        Role role = Role.valueOf(roleName.toUpperCase());
        List<User> users = userRepository.findByRolesIn(Collections.singleton(role));
        return users.stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
