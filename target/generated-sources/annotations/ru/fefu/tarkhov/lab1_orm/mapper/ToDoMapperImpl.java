package ru.fefu.tarkhov.lab1_orm.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.fefu.tarkhov.lab1_orm.dto.ToDoResponse;
import ru.fefu.tarkhov.lab1_orm.dto.command.CreateToDoCommand;
import ru.fefu.tarkhov.lab1_orm.entity.ToDo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-09T18:46:15+1000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ToDoMapperImpl implements ToDoMapper {

    @Override
    public ToDo map(CreateToDoCommand source) {
        if ( source == null ) {
            return null;
        }

        ToDo.ToDoBuilder toDo = ToDo.builder();

        toDo.name( source.getName() );
        toDo.description( source.getDescription() );
        toDo.groupName( source.getGroupName() );

        return toDo.build();
    }

    @Override
    public ToDoResponse map(ToDo source) {
        if ( source == null ) {
            return null;
        }

        ToDoResponse toDoResponse = new ToDoResponse();

        toDoResponse.setId( source.getId() );
        toDoResponse.setName( source.getName() );
        toDoResponse.setDescription( source.getDescription() );
        toDoResponse.setGroupName( source.getGroupName() );

        return toDoResponse;
    }
}
