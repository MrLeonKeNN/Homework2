package com.homework2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework2.dto.AttractionDto;
import com.homework2.enums.AttractionNames;
import com.homework2.enums.AttractionsEnum;
import com.homework2.exeptions.EntityNotFoundException;
import com.homework2.service.impl.AttractionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AttractionController.class)
class AttractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttractionServiceImpl attractionService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void saveAttraction_success() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("Attraction Name");

        mockMvc.perform(MockMvcRequestBuilders.post("/attractions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attractionDto)))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteAttraction_success() throws Exception {
        Mockito.doNothing().when(attractionService).delete(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/attractions/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAttraction_notFound() throws Exception {
        Mockito.doThrow(new EntityNotFoundException("Attraction not found")).when(attractionService).delete(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/attractions/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getByLocality_success() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("Attraction Name");

        Mockito.when(attractionService.getByLocality(anyLong())).thenReturn(List.of(attractionDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/attractions/locality/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Attraction Name"));
    }

    @Test
    public void editAttraction_success() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("Updated Attraction Name");

        mockMvc.perform(MockMvcRequestBuilders.put("/attractions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attractionDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void editAttraction_notFound() throws Exception {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setName("Updated Attraction Name");

        Mockito.doThrow(new EntityNotFoundException("Attraction not found")).when(attractionService).edit(anyLong(), any(AttractionDto.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/attractions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attractionDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getByFilter_invalidFilter() throws Exception {
        Mockito.when(attractionService.getBySort(any(AttractionNames.class), any(AttractionsEnum.class)))
                .thenThrow(new IllegalArgumentException("Invalid filter"));

        mockMvc.perform(MockMvcRequestBuilders.get("/attractions/sort/type/INVALID"))
                .andExpect(status().isBadRequest());
    }
}