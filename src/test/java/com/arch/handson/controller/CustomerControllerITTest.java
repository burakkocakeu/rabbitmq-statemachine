package com.arch.handson.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration Test - Using WebMvcTest annotation allows configuration part to only configure Mvc related configurations.
 *
 * Integration testing means checking if different modules are working fine when combined together as a group.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerITTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void list() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/hello");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("Hello, world", result.getResponse().getContentAsString());
    }

    @Test
    public void listWithName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello?name=bk"))
                .andExpect(MockMvcResultMatchers.content().string("Hello, bk"));
    }
}