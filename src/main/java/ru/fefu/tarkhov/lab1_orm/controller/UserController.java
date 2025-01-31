// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package ru.fefu.tarkhov.lab1_orm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fefu.tarkhov.lab1_orm.dto.UserDto;
import ru.fefu.tarkhov.lab1_orm.dto.UserResponse;
import ru.fefu.tarkhov.lab1_orm.dto.command.CreateUserCommand;
import ru.fefu.tarkhov.lab1_orm.dto.command.UpdateUserCommand;
import ru.fefu.tarkhov.lab1_orm.exception.UserNotFoundException;
import ru.fefu.tarkhov.lab1_orm.service.UserAggregate;
import ru.fefu.tarkhov.lab1_orm.service.UserProjection;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserAggregate aggregate;

    private final UserProjection projection;

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
         return projection.findBy(id);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return projection.findAll();
    }

    @PostMapping
    public Long addUser(@RequestBody CreateUserCommand command) {
        return aggregate.handleUserCreateCommand(command);
    }

    @PutMapping
    public void updateUser(@RequestBody UpdateUserCommand command) {
        aggregate.handleUserUpdateCommand(command);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        aggregate.handleUserDeleteCommand(id);
    }

    @GetMapping("/test-user")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<String> user() {
        String response = "User";
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test-admin")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> admin() {
        String response = "Admin";
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(response);
    }

}
