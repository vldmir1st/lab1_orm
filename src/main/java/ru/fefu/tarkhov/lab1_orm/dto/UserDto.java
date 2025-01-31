package ru.fefu.tarkhov.lab1_orm.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private Long id;

    @Email
    private String email;

    private LocalDate birthDate;

    private boolean isMale;

}
