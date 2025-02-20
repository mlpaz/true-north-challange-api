package com.truenorth.challenge.api.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.truenorth.challenge.AbstractIntegrationTest;
import com.truenorth.challenge.api.resource.dto.UserTokenDTO;
import com.truenorth.challenge.api.resource.request.LogInRequest;
import com.truenorth.challenge.factory.UserFactory;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HTTPUserControllerTest extends AbstractIntegrationTest {

    private static final String USER_PATH = "/api/v1/user";

    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void givenValidUserWhenLoginThenCheckResponse() throws Exception {
        LogInRequest request = UserFactory.buildLogInRequest();
        String requestJson = objectMapper.writeValueAsString(request);

        MvcResult result = this.mockMvc.perform(post(USER_PATH + "/login")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        UserTokenDTO responseDTO = objectMapper.readValue(contentAsString, UserTokenDTO.class);

        assertTrue(responseDTO.getToken() != null && !responseDTO.getToken().isEmpty());

    }

    @Test
    void givenInvalidUserWhenLoginThenCheckStatus() throws Exception {
        LogInRequest request = UserFactory.buildLogInRequest();
        request.setPassword("123");
        String requestJson = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(post(USER_PATH + "/login")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}
<a class="app-aware-link " href="https://www.linkedin.com/in/paloma-sof%C3%ADa-celi-a41862153?miniProfileUrn=urn%3Ali%3Afs_miniProfile%3AACoAACT3yVEBRrOIjTZkqkAQOWrEwhs5RBu7OCM" data-test-app-aware-link="">
<span dir="ltr"><span aria-hidden="true"><!---->Paloma Sofía Celi<!----></span><span class="visually-hidden"><!---->View Paloma Sofía Celi’s profile<!----></span></span>
</a>