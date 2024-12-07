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
import ru.fefu.tarkhov.lab1_orm.entity.ToDo;
import ru.fefu.tarkhov.lab1_orm.entity.ToDoGroup;
import ru.fefu.tarkhov.lab1_orm.entity.User;
import ru.fefu.tarkhov.lab1_orm.repo.ToDoRepo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/todo-groups/{groupId}/todos")
public class TodoController {

    private final ToDoRepo repo;

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDo(@PathVariable Long userId, @PathVariable Long groupId, @PathVariable Long id) {
        var todo = repo.findById(id)
                .filter(ent -> {
                    var todoGroup = ent.getToDoGroup();
                    var user = todoGroup.getUser();
                    return (user.getId().equals(userId) && todoGroup.getId().equals(groupId));
                })
                .orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<Long> addTodo(@PathVariable Long userId, @PathVariable Long groupId, @RequestBody ToDo todo) {
        todo.setToDoGroup(
                new ToDoGroup().setId(groupId).setUser(
                        new User().setId(userId)
                )
        );
        var todoInDb = repo.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todoInDb.getId());
    }

}
