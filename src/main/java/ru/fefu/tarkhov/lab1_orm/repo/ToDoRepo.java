// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package ru.fefu.tarkhov.lab1_orm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fefu.tarkhov.lab1_orm.entity.ToDo;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Long> {

    List<ToDo> findAllByUser_Id(Long userId);

    List<ToDo> findAllByUser_IdAndGroupName(Long userId, String groupName);

}
