package ru.fefu.tarkhov.lab1_orm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fefu.tarkhov.lab1_orm.dto.UserResponse;
import ru.fefu.tarkhov.lab1_orm.entity.User;
import ru.fefu.tarkhov.lab1_orm.exception.UserNotFoundException;
import ru.fefu.tarkhov.lab1_orm.mapper.UserMapper;
import ru.fefu.tarkhov.lab1_orm.repo.UserRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProjection {

    private final UserRepo repo;

    private final UserMapper mapper;

    public UserResponse findBy(Long id) {
        var user = repo.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return mapper.map(user);
    }

    public List<UserResponse> findAll() {
        List<User> users = repo.findAll();
        return users.stream()
                .map(mapper::map)
                .toList();
    }

}
