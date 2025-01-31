package ru.fefu.tarkhov.lab1_orm.dto.command;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserCommand {

    @Email
    private String email;

    private String sex;

    private LocalDate birthDate;

}
