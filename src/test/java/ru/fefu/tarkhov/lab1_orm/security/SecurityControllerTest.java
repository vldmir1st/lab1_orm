package ru.fefu.tarkhov.lab1_orm.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.fefu.tarkhov.lab1_orm.config.SecurityConfig;
import ru.fefu.tarkhov.lab1_orm.controller.UserController;
import ru.fefu.tarkhov.lab1_orm.dto.UserResponse;
import ru.fefu.tarkhov.lab1_orm.service.UserAggregate;
import ru.fefu.tarkhov.lab1_orm.service.UserProjection;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
class SecurityControllerTest {

    @MockBean
    UserAggregate aggregate;

    @MockBean
    UserProjection projection;

    @Autowired
    MockMvc mockMvc;

    private static final String EXPIRED_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI4SVh3V05hcG9aVHFwbVgwRGlxamoydjNiX0Fya2ZQVkFweFJhZldsQXp3In0.eyJleHAiOjE3MzkxMDMzNzMsImlhdCI6MTczOTEwMzA3MywianRpIjoiMzQ1ZmJhZjItZTk3My00MzUwLTlhZjItMDUyMTA0YjYxZDhjIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9zaXRfbGFiIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjMwYjI1NWUyLWFjMzEtNGU3OS1hNjMwLTkxZWZmZTQzYzJlNiIsInR5cCI6IkJlYXJlciIsImF6cCI6Im15X2NsaWVudCIsInNlc3Npb25fc3RhdGUiOiI0NzI3MzRjMC1mZWJiLTQ5MGUtOTQwYy1mZWU0N2Y3MzcyYzkiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIi8qIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImRlZmF1bHQtcm9sZXMtc2l0X2xhYiIsInVtYV9hdXRob3JpemF0aW9uIiwidXNlciJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjQ3MjczNGMwLWZlYmItNDkwZS05NDBjLWZlZTQ3ZjczNzJjOSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlciJ9.FTfh-NCsFkbK0WCbIQ0KpYE-QJdlpxyuZ079bLQkUMulFA3ol8yOWgdJq3y1DgxDMURE9iT4pRYndTDBjBzQ2rD7Dw8seWMkiqLXByZM3aXHP1F6szZvktsM4V6OojT-PVjHtaci7ISc5b0gZt_cjCM0y8rtzxCySldXezZsRezTUxHnuL9ppMWtawWyhqiEPAU55N5PFH3QE9o6HumwDGGkLxivJ6mJNSXll8h2dBkaNlRj7_bGvvJAgbegYTWnWguuX7Yog50BfIXcXPwTWqP63hDsCaD7zDjhS3FYBatKcURxn4mnKiypo1tYRDb4-ExwBimXDXR-cT2WIxMpAQ";

    @Test
    @WithMockUser
    void authorizedUserTest() throws Exception {
        when(projection.findBy(any())).thenReturn((new UserResponse()));

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void unauthorizedUserTest() throws Exception {
        when(projection.findBy(any())).thenReturn((new UserResponse()));

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"admin"})
    void adminRoleTest() throws Exception {
        mockMvc.perform(get("/users/test-admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void userRoleProhibitedTest() throws Exception {
        mockMvc.perform(get("/users/test-admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    void expiredTokenTest() throws Exception {
        when(projection.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/users")
                        .header("Authorization", "Bearer " + EXPIRED_TOKEN))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void withoutBearerTest() throws Exception {
        when(projection.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/users")
                        .header("Authorization", EXPIRED_TOKEN))
                .andExpect(status().isUnauthorized());
    }

}
