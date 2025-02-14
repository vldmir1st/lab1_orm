// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package ru.fefu.tarkhov.lab1_orm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fefu.tarkhov.lab1_orm.dto.ToDoResponse;
import ru.fefu.tarkhov.lab1_orm.dto.UserResponse;
import ru.fefu.tarkhov.lab1_orm.dto.command.CreateToDoCommand;
import ru.fefu.tarkhov.lab1_orm.dto.command.UpdateToDoCommand;
import ru.fefu.tarkhov.lab1_orm.dto.query.TodosQuery;
import ru.fefu.tarkhov.lab1_orm.exception.TodoNotFoundException;
import ru.fefu.tarkhov.lab1_orm.service.ToDoAggregate;
import ru.fefu.tarkhov.lab1_orm.service.ToDoProjection;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final ToDoAggregate aggregate;

    private final ToDoProjection projection;

    @GetMapping("/{id}")
    public ToDoResponse getToDo(@PathVariable Long id) {
        return projection.findBy(id);
    }

    @GetMapping
    public List<ToDoResponse> getTodos(TodosQuery query) {
        return projection.handle(query);
    }

    @PostMapping
    public Long createToDo(@RequestBody CreateToDoCommand command) {
        return aggregate.handleToDoCreateCommand(command);
    }

    @PutMapping
    public void updateToDo(@RequestBody UpdateToDoCommand command) {
        aggregate.handleToDoUpdateCommand(command);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable Long id) {
        aggregate.handleToDoDeleteCommand(id);
    }

}
