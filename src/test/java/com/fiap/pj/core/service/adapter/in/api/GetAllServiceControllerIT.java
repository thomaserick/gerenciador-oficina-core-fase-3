package com.fiap.pj.core.service.adapter.in.api;


import com.fiap.pj.core.service.util.factory.ServiceTestFactory;
import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.util.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Sql(scripts = {"classpath:db/it/services/create_services.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@IntegrationTest
class GetAllServiceControllerIT {

    @Autowired
    private MockMvc mock;

    @Test
    void shouldGetAllServices() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(ServiceController.PATH)).param("name", "troca").param("active", "true")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.items[0].id", is("84c052e5-3aa5-465b-9e14-6551e4ba1a65")))
                .andExpect(jsonPath("$.items[0].description", is(ServiceTestFactory.DESCRIPTION)))
                .andExpect(jsonPath("$.items[0].observation", is(ServiceTestFactory.OBSERVATION)))
                .andExpect(jsonPath("$.items[0].active", is(true)));
    }

}
