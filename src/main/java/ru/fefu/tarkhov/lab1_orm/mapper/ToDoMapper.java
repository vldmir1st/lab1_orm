package ru.fefu.tarkhov.lab1_orm.mapper;

import org.mapstruct.Mapper;
import ru.fefu.tarkhov.lab1_orm.dto.ToDoResponse;
import ru.fefu.tarkhov.lab1_orm.dto.command.CreateToDoCommand;
import ru.fefu.tarkhov.lab1_orm.dto.command.UpdateToDoCommand;
import ru.fefu.tarkhov.lab1_orm.entity.ToDo;

@Mapper(componentModel = "spring")
public interface ToDoMapper {

    ToDo map(CreateToDoCommand source);

    ToDoResponse map(ToDo source);

    default ToDo merge(UpdateToDoCommand newInfo, ToDo oldInfo) {
        if (newInfo.getName() != null && !newInfo.getName().isBlank()) {
            oldInfo.setName(newInfo.getName());
        }
        if (newInfo.getDescription() != null && !newInfo.getDescription().isBlank()) {
            oldInfo.setDescription(newInfo.getDescription());
        }
        if (newInfo.getGroupName() != null && !newInfo.getGroupName().isBlank()) {
            oldInfo.setGroupName(newInfo.getGroupName());
        }

        return oldInfo;
    }

}
