package com.homework2.converters.impl;

import com.homework2.converters.api.Converter;
import com.homework2.dto.LocalityDto;
import com.homework2.models.Locality;
import org.springframework.stereotype.Component;

/**
 * Converter for transforming objects of type {@link Locality} to {@link LocalityDto} and vice versa.
 * <p>
 * This class is marked as a Spring component, which allows it to be used as a bean within the Spring context.
 * Implements the {@link Converter} interface, defining methods for converting data between {@link Locality} and {@link LocalityDto}.
 * </p>
 */
@Component
public class LocalityConverter implements Converter<Locality, LocalityDto> {

    /**
     * Converts a {@link Locality} object to a {@link LocalityDto} object.
     * <p>
     * This method currently returns {@code null}. Needs implementation to provide conversion logic.
     * </p>
     *
     * @param locality the object to be converted.
     * @return a {@link LocalityDto} object containing data from the {@link Locality}.
     */
    @Override
    public LocalityDto toDto(Locality locality) {
        return null;
    }

    /**
     * Converts a {@link LocalityDto} object to a {@link Locality} object.
     *
     * @param localityDto the object to be converted.
     * @return a {@link Locality} object containing data from the {@link LocalityDto}.
     */
    @Override
    public Locality fromDto(LocalityDto localityDto) {
        return Locality.builder()
                .settlement(localityDto.getSettlement())
                .region(localityDto.getRegion())
                .width(localityDto.getWidth())
                .height(localityDto.getHeight())
                .build();
    }
}
