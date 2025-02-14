package ru.fefu.tarkhov.lab1_orm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fefu.tarkhov.lab1_orm.dto.ToDoResponse;
import ru.fefu.tarkhov.lab1_orm.dto.query.TodosQuery;
import ru.fefu.tarkhov.lab1_orm.entity.ToDo;
import ru.fefu.tarkhov.lab1_orm.exception.TodoNotFoundException;
import ru.fefu.tarkhov.lab1_orm.mapper.ToDoMapper;
import ru.fefu.tarkhov.lab1_orm.repo.ToDoRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoProjection {

    private final ToDoRepo repo;

    private final ToDoMapper mapper;

    public ToDoResponse findBy(Long id) {
        var todo = repo.findById(id)
                .orElseThrow(TodoNotFoundException::new);
        return mapper.map(todo);
    }

    public List<ToDoResponse> handle(TodosQuery query) {
        List<ToDo> todos = (query.getGroupName() != null && !query.getGroupName().isBlank())
                ? repo.findAllByUser_IdAndGroupName(query.getUserId(), query.getGroupName())
                : repo.findAllByUser_Id(query.getUserId());

        return todos.stream()
                .map(mapper::map)
                .toList();
    }

}
