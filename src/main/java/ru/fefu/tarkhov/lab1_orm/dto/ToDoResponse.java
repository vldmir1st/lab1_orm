package ru.fefu.tarkhov.lab1_orm.dto;

import lombok.Data;

@Data
public class ToDoResponse {

    private Long id;

    private String name;

    private String description;

    private String groupName;

}
