package ru.fefu.tarkhov.lab1_orm.dto.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserCommand {

    @NotNull
    private Long id;

    private String email;

    private String sex;

    private LocalDate birthDate;

}
