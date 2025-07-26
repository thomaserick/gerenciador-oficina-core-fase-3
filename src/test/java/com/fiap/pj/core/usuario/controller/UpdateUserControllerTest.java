package com.fiap.pj.core.usuario.controller;


import com.fiap.pj.core.usuario.adapter.in.api.UserController;
import com.fiap.pj.core.usuario.usecase.UpdateUserUseCase;
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

import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.oneCreateUserCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UpdateUserControllerTest {

    @Mock
    private UpdateUserUseCase updateUserUseCase;

    @InjectMocks
    private UserController userController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void shouldUpdateUser() throws Exception {

        mock.perform(put(
                TestUtils.buildURL(UserController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(oneCreateUserCommand()))).andExpect(status().is2xxSuccessful());

    }

}
