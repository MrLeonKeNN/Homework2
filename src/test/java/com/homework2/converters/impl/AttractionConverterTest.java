package com.homework2.converters.impl;

import com.homework2.dto.AttractionDto;
import com.homework2.enums.AttractionsEnum;
import com.homework2.models.Attraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AttractionConverterTest {

    private AttractionConverter attractionConverter;

    @BeforeEach
    public void setUp() {
        attractionConverter = new AttractionConverter();
    }


    @Test
    public void testToDto() {
        Attraction attraction = Attraction.builder()
                .name("Eiffel Tower")
                .shortDescription("Famous landmark in Paris")
                .typeAttraction(AttractionsEnum.PALACES)
                .dateOfCreation(LocalDateTime.now())
                .build();

        AttractionDto attractionDto = attractionConverter.toDto(attraction);

        assertNotNull(attractionDto);
        assertEquals(attraction.getName(), attractionDto.getName());
        assertEquals(attraction.getShortDescription(), attractionDto.getShortDescription());
        assertEquals(attraction.getTypeAttraction(), attractionDto.getTypeAttraction());
    }

    @Test
    public void testFromDto() {
        AttractionDto attractionDto = AttractionDto.builder()
                .name("Louvre Museum")
                .shortDescription("One of the largest art museums in the world")
                .typeAttraction(AttractionsEnum.MUSEUMS)
                .build();

        Attraction attraction = attractionConverter.fromDto(attractionDto);

        assertNotNull(attraction);
        assertEquals(attractionDto.getName(), attraction.getName());
        assertEquals(attractionDto.getShortDescription(), attraction.getShortDescription());
        assertEquals(attractionDto.getTypeAttraction(), attraction.getTypeAttraction());
        assertNotNull(attraction.getDateOfCreation());
    }
}