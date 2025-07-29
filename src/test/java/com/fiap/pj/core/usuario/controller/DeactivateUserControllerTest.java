package com.fiap.pj.core.usuario.controller;


import com.fiap.pj.core.usuario.adapter.in.api.UserController;
import com.fiap.pj.core.usuario.usecase.DeactivateUserUseCase;
import com.fiap.pj.core.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DeactivateUserControllerTest {

    @Mock
    private DeactivateUserUseCase deactivateUserUseCase;

    @InjectMocks
    private UserController userController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void ShouldDeactivateUser() throws Exception {
        mock.perform(post(
                TestUtils.buildURL(UserController.PATH, UUID.randomUUID().toString(), "deactivate"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().is2xxSuccessful());


    }

}
