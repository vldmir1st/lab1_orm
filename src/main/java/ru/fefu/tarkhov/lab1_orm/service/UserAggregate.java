package ru.fefu.tarkhov.lab1_orm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fefu.tarkhov.lab1_orm.dto.command.CreateUserCommand;
import ru.fefu.tarkhov.lab1_orm.dto.command.UpdateUserCommand;
import ru.fefu.tarkhov.lab1_orm.exception.UserNotFoundException;
import ru.fefu.tarkhov.lab1_orm.mapper.UserMapper;
import ru.fefu.tarkhov.lab1_orm.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class UserAggregate {

    private final UserRepo repo;

    private final UserMapper mapper;

    public Long handleUserCreateCommand(CreateUserCommand command) {
        var user = mapper.map(command);
        user = repo.save(user);
        return user.getId();
    }

    public void handleUserUpdateCommand(UpdateUserCommand command) {
        var user = repo.findById(command.getId()).orElseThrow(UserNotFoundException::new);
        var updatedUser = mapper.map(command)
                .setId(user.getId());
        repo.save(updatedUser);
    }

    public void handleUserDeleteCommand(Long id) {
        repo.deleteById(id);
    }

}
