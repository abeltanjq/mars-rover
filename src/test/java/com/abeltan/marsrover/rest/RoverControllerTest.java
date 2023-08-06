package com.abeltan.marsrover.rest;

import com.abeltan.marsrover.entity.Rover;
import com.abeltan.marsrover.response.RoverConfigurationErrorResponse;
import com.abeltan.marsrover.utility.RoverFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void createRoverSuccess() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/rovers?create=-1,2,S"))
                .andExpect(status().isCreated()).andReturn();

        MockHttpServletResponse mockResponse = mvcResult.getResponse();
        assertEquals(201, mockResponse.getStatus());

        Rover r = RoverFactory.create("-1,2,S");
        r.setId(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(r);
        assertEquals(json, mockResponse.getContentAsString());
    }

    @Test
    void createRoverFailure() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/rovers?create=-1,2,T"))
                .andExpect(status().is4xxClientError()).andReturn();

        MockHttpServletResponse mockResponse = mvcResult.getResponse();
        assertEquals(400, mockResponse.getStatus());

        RoverConfigurationErrorResponse response = new RoverConfigurationErrorResponse(
                400, "Invalid rover creation configuration."
        );
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(response);
        assertEquals(message, mockResponse.getContentAsString());
    }

    @Test
    @Order(2)
    void createRoverCannotCreateAnotherRoverOnTheSameSpot() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/rovers?create=-1,2,W"))
                .andExpect(status().isBadRequest()).andReturn();

        MockHttpServletResponse mockResponse = mvcResult.getResponse();
        assertEquals(400, mockResponse.getStatus());

        RoverConfigurationErrorResponse response = new RoverConfigurationErrorResponse(
                400, "Existing rover on the same coordinates."
        );
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(response);
        assertEquals(message, mockResponse.getContentAsString());
    }

    @Test
    @Order(2)
    void getCurrentPositionOfExistingRover() throws Exception {
        // R1 is created in the first test
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rovers/R1"))
                .andExpect(status().isOk()).andReturn();
        MockHttpServletResponse mockResponse = mvcResult.getResponse();
        assertEquals(200, mockResponse.getStatus());

        Rover r = RoverFactory.create("-1,2,S");
        r.setId(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(r);
        assertEquals(json, mockResponse.getContentAsString());
    }

    @Test
    void getCurrentPositionOfNonExistentRoverReturns404() throws Exception {
        // R1 is created in the first test
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rovers/R99"))
                .andExpect(status().isNotFound()).andReturn();
        MockHttpServletResponse mockResponse = mvcResult.getResponse();
        assertEquals(404, mockResponse.getStatus());
    }

    @Test
    void moveRoverReturnsNotFoundWhenRoverIsNotPresent() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/rovers/R99?command=f"))
                .andExpect(status().isNotFound()).andReturn();
        MockHttpServletResponse mockResponse = mvcResult.getResponse();
        assertEquals(404, mockResponse.getStatus());
    }

    @Test
    @Order(4)
    void moveRoverMovesCorrectly() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/rovers/R1?command=f,l,b,r"))
                .andExpect(status().isOk()).andReturn();
        MockHttpServletResponse mockResponse = mvcResult.getResponse();
        assertEquals(200, mockResponse.getStatus());

        Rover r = RoverFactory.create("-2,1,S");
        r.setId(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(r);
        assertEquals(json, mockResponse.getContentAsString());
    }

    @Test
    @Order(5)
    void moveRoverStopsWhenEncounteringAnotherRover() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rovers?create=-2,-1,N"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/rovers/R2?command=f,f,f,f"))
                .andExpect(status().isOk()).andReturn();
        MockHttpServletResponse mockResponse = mvcResult.getResponse();

        Rover r = RoverFactory.create("-2,0,N");
        r.setId(2);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(r);
        assertEquals(json, mockResponse.getContentAsString());
    }
}