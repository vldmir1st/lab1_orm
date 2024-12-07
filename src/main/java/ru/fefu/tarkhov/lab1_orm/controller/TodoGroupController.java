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
import ru.fefu.tarkhov.lab1_orm.entity.ToDoGroup;
import ru.fefu.tarkhov.lab1_orm.entity.User;
import ru.fefu.tarkhov.lab1_orm.repo.ToDoGroupRepo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/todo-groups")
public class TodoGroupController {

    private final ToDoGroupRepo repo;

    @GetMapping("/{id}")
    public ResponseEntity<ToDoGroup> getToDoGroup(@PathVariable Long userId, @PathVariable Long id) {
        var toDoGroup = repo.findById(id)
                .filter(ent -> ent.getUser().getId().equals(userId))
                .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(toDoGroup);
    }

    @PostMapping
    public ResponseEntity<Long> addToDoGroup(@PathVariable Long userId, @RequestBody ToDoGroup toDoGroup) {
        toDoGroup.setUser(new User().setId(userId));
        var toDoGroupInDb = repo.save(toDoGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoGroupInDb.getId());
    }

}
