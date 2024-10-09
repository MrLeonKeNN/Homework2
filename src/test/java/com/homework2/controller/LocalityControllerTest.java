package com.homework2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework2.dto.LocalityDto;
import com.homework2.service.impl.LocalityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LocalityController.class)
class LocalityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocalityServiceImpl localityServicep;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveLocality_success() throws Exception{
        LocalityDto localityDto = LocalityDto.builder().region("Moscow").settlement("Moscow").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/locality")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(localityDto)))
                .andExpect(status().isOk());
    }
    @Test
    void saveAttraction_validationFailure() throws Exception{
        LocalityDto localityDto = LocalityDto.builder().build();

        mockMvc.perform(MockMvcRequestBuilders.post("/locality")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(localityDto)))
                .andExpect(status().isBadRequest());
    }

}