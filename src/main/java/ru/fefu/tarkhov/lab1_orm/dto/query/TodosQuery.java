package ru.fefu.tarkhov.lab1_orm.dto.query;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TodosQuery {

    @NotNull
    private Long userId;

    private String groupName;

}
