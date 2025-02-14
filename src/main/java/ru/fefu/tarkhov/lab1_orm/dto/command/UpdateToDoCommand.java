package ru.fefu.tarkhov.lab1_orm.dto.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateToDoCommand {

    @NotNull
    private Long id;

    private String name;

    private String description;

    private String groupName;

}
