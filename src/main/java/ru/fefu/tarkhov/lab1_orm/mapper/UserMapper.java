package ru.fefu.tarkhov.lab1_orm.mapper;

import org.mapstruct.Mapper;
import ru.fefu.tarkhov.lab1_orm.dto.UserDto;
import ru.fefu.tarkhov.lab1_orm.dto.UserResponse;
import ru.fefu.tarkhov.lab1_orm.dto.command.CreateUserCommand;
import ru.fefu.tarkhov.lab1_orm.dto.command.UpdateUserCommand;
import ru.fefu.tarkhov.lab1_orm.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserDto source);

    User map(CreateUserCommand source);

    User map(UpdateUserCommand source);

    UserResponse map(User source);

}
