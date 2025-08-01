package com.fiap.pj.core.customer.adapter.in.api;


import com.fiap.pj.core.customer.util.factory.CustomerTestFactory;
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


@Sql(scripts = {"classpath:db/it/user/create_customers.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@IntegrationTest
class GetAllCustomerControllerIT {

    @Autowired
    private MockMvc mock;

    @Test
    void shouldGetAllCustomers() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(CustomerController.PATH)).param("name", "haku").param("active", "true")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.items[0].id", is("113e83b4-02e3-4059-8498-f4beafbb5ed9")))
                .andExpect(jsonPath("$.items[0].name", is(CustomerTestFactory.NAME)))
                .andExpect(jsonPath("$.items[0].email", is(CustomerTestFactory.E_MAIL)))
                .andExpect(jsonPath("$.items[0].phone", is(CustomerTestFactory.PHONE)))
                .andExpect(jsonPath("$.items[0].address", is(CustomerTestFactory.ADDRESS)))
                .andExpect(jsonPath("$.items[0].identificationDocument.number", is(CustomerTestFactory.IDENTIFICATION_DOCUMENT_NUMBER)))
                .andExpect(jsonPath("$.items[0].active", is(true)));
    }

}
