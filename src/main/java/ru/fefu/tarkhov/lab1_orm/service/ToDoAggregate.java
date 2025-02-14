package ru.fefu.tarkhov.lab1_orm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fefu.tarkhov.lab1_orm.dto.command.CreateToDoCommand;
import ru.fefu.tarkhov.lab1_orm.dto.command.UpdateToDoCommand;
import ru.fefu.tarkhov.lab1_orm.exception.TodoNotFoundException;
import ru.fefu.tarkhov.lab1_orm.exception.UserNotFoundException;
import ru.fefu.tarkhov.lab1_orm.mapper.ToDoMapper;
import ru.fefu.tarkhov.lab1_orm.repo.ToDoRepo;
import ru.fefu.tarkhov.lab1_orm.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class ToDoAggregate {

    private final UserRepo userRepo;

    private final ToDoRepo toDoRepo;

    private final ToDoMapper mapper;

    public Long handleToDoCreateCommand(CreateToDoCommand command) {
        var user = userRepo.findById(command.getUserId())
                .orElseThrow(UserNotFoundException::new);
        var todo = mapper.map(command)
                .setUser(user);

        return toDoRepo.save(todo).getId();
    }

    public void handleToDoUpdateCommand(UpdateToDoCommand command) {
        var todo = toDoRepo.findById(command.getId())
                .orElseThrow(TodoNotFoundException::new);
        var updatedToDo = mapper.merge(command, todo);

        toDoRepo.save(updatedToDo);
    }

    public void handleToDoDeleteCommand(Long id) {
        toDoRepo.deleteById(id);
    }

}
