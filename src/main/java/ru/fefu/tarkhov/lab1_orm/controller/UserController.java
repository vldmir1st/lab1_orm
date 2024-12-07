package ru.fefu.tarkhov.lab1_orm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fefu.tarkhov.lab1_orm.entity.User;
import ru.fefu.tarkhov.lab1_orm.repo.UserRepo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepo repo;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
         var user = repo.findById(id).orElseThrow(RuntimeException::new);
         return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Long> addUser(@RequestBody User user) {
        var userInDb = repo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userInDb.getId());
    }

}
