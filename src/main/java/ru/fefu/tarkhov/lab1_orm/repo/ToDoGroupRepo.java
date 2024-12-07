package ru.fefu.tarkhov.lab1_orm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fefu.tarkhov.lab1_orm.entity.ToDoGroup;

@Repository
public interface ToDoGroupRepo extends JpaRepository<ToDoGroup, Long> {

}
