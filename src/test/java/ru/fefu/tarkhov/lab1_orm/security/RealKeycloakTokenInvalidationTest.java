package ru.fefu.tarkhov.lab1_orm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RealKeycloakTokenInvalidationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String EXPIRED_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI4SVh3V05hcG9aVHFwbVgwRGlxamoydjNiX0Fya2ZQVkFweFJhZldsQXp3In0.eyJleHAiOjE3MzkxMDMzNzMsImlhdCI6MTczOTEwMzA3MywianRpIjoiMzQ1ZmJhZjItZTk3My00MzUwLTlhZjItMDUyMTA0YjYxZDhjIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9zaXRfbGFiIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjMwYjI1NWUyLWFjMzEtNGU3OS1hNjMwLTkxZWZmZTQzYzJlNiIsInR5cCI6IkJlYXJlciIsImF6cCI6Im15X2NsaWVudCIsInNlc3Npb25fc3RhdGUiOiI0NzI3MzRjMC1mZWJiLTQ5MGUtOTQwYy1mZWU0N2Y3MzcyYzkiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIi8qIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImRlZmF1bHQtcm9sZXMtc2l0X2xhYiIsInVtYV9hdXRob3JpemF0aW9uIiwidXNlciJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjQ3MjczNGMwLWZlYmItNDkwZS05NDBjLWZlZTQ3ZjczNzJjOSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlciJ9.FTfh-NCsFkbK0WCbIQ0KpYE-QJdlpxyuZ079bLQkUMulFA3ol8yOWgdJq3y1DgxDMURE9iT4pRYndTDBjBzQ2rD7Dw8seWMkiqLXByZM3aXHP1F6szZvktsM4V6OojT-PVjHtaci7ISc5b0gZt_cjCM0y8rtzxCySldXezZsRezTUxHnuL9ppMWtawWyhqiEPAU55N5PFH3QE9o6HumwDGGkLxivJ6mJNSXll8h2dBkaNlRj7_bGvvJAgbegYTWnWguuX7Yog50BfIXcXPwTWqP63hDsCaD7zDjhS3FYBatKcURxn4mnKiypo1tYRDb4-ExwBimXDXR-cT2WIxMpAQ";

    @org.junit.jupiter.api.Test
    void expiredTokenTest() throws Exception {
        mockMvc.perform(get("/users")
                        .header("Authorization", "Bearer " + EXPIRED_TOKEN))
                .andExpect(status().isUnauthorized());
    }

    @org.junit.jupiter.api.Test
    void withoutBearerTest() throws Exception {

        mockMvc.perform(get("/users")
                        .header("Authorization", EXPIRED_TOKEN))
                .andExpect(status().isUnauthorized());
    }

}
