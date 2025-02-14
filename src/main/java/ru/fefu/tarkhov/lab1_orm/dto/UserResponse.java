package ru.fefu.tarkhov.lab1_orm.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {

    private Long id;

    private String email;

    private LocalDate birthDate;

    private String sex;

}
