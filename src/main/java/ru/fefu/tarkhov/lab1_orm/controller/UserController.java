// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package ru.fefu.tarkhov.lab1_orm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fefu.tarkhov.lab1_orm.entity.User;
import ru.fefu.tarkhov.lab1_orm.exception.UserNotFoundException;
import ru.fefu.tarkhov.lab1_orm.repo.UserRepo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepo repo;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
         var user = repo.findById(id).orElseThrow(UserNotFoundException::new);
         return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Long> addUser(@RequestBody User user) {
        var userInDb = repo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userInDb.getId());
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
