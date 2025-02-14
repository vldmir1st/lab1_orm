package ru.fefu.tarkhov.lab1_orm.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateToDoCommand {

    @NotNull
    private Long userId;

    @NotBlank
    private String name;

    private String description;

    private String groupName;

}
