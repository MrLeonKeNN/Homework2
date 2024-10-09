package com.homework2.converters.impl;

import com.homework2.converters.api.Converter;
import com.homework2.dto.AttractionDto;
import com.homework2.models.Attraction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Converter for transforming objects of type {@link Attraction} to {@link AttractionDto} and vice versa.
 * <p>
 * This class is marked as a Spring component, which allows it to be used as a bean within the Spring context.
 * It implements the {@link Converter} interface, which defines methods for data transformation between two types.
 * </p>
 */
@Component
public class AttractionConverter implements Converter<Attraction, AttractionDto> {

    /**
     * Converts an {@link Attraction} object to an {@link AttractionDto} object.
     *
     * @param attraction the object to be converted.
     * @return an {@link AttractionDto} object containing data from the {@link Attraction}.
     */
    @Override
    public AttractionDto toDto(Attraction attraction) {
        return AttractionDto.builder()
                .name(attraction.getName())
                .shortDescription(attraction.getShortDescription())
                .typeAttraction(attraction.getTypeAttraction())
                .build();
    }

    /**
     * Converts an {@link AttractionDto} object to an {@link Attraction} object.
     * Sets the creation date to the current {@link LocalDateTime#now()}.
     *
     * @param attractionDto the object to be converted.
     * @return an {@link Attraction} object containing data from the {@link AttractionDto}.
     */
    @Override
    public Attraction fromDto(AttractionDto attractionDto) {
        return Attraction.builder()
                .name(attractionDto.getName())
                .shortDescription(attractionDto.getShortDescription())
                .typeAttraction(attractionDto.getTypeAttraction())
                .dateOfCreation(LocalDateTime.now())
                .build();
    }
}
