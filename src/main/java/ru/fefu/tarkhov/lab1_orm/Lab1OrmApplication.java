// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package ru.fefu.tarkhov.lab1_orm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Лаба №1 (ORM + Swagger)",
        version = "0.0.1-SNAPSHOT"
))
public class Lab1OrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab1OrmApplication.class, args);
    }

}
