package com.findserv.root.controller;

import com.findserv.root.DTO.AdminCreateUserDto;
import com.findserv.root.DTO.UserDto;
import com.findserv.root.DTO.UserRegistrationDto;
import com.findserv.root.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody UserRegistrationDto dto) {
        return userService.registerUser(dto);
    }

    @PostMapping("/adminregistration")
    public UserDto createUserByAdmin(@RequestBody AdminCreateUserDto dto) {
        return userService.createUserByAdmin(dto);
    }

    @PostMapping("/admin-reg/batch")
    public ResponseEntity<List<UserDto>> createUsersBatch(
            @RequestBody List<AdminCreateUserDto> dtos) {

        List<UserDto> createdUsers = dtos.stream()
                .map(userService::createUserByAdmin) // call existing single-user method
                .collect(Collectors.toList());

        return ResponseEntity.ok(createdUsers);
    }
    @GetMapping("/user-roles")
    public ResponseEntity<List<UserDto>> getUserRoles(@RequestParam String role) {
        List<UserDto> usersWithRole = userService.getUsersByRole(role);
        return ResponseEntity.ok(usersWithRole);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

