package com.homework2.converters.impl;

import com.homework2.dto.LocalityDto;
import com.homework2.models.Locality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LocalityConverterTest {


    private LocalityConverter localityConverter;

    @BeforeEach
    public void setUp() {
        localityConverter = new LocalityConverter();
    }

    @Test
    public void testFromDto_ValidInput() {
        // Создаем объект LocalityDto для конвертации
        LocalityDto localityDto = LocalityDto.builder()
                .settlement("Springfield")
                .region("Some Region")
                .width(12.34)
                .height(56.78)
                .build();

        // Вызываем метод fromDto
        Locality locality = localityConverter.fromDto(localityDto);

        // Проверяем корректность конвертации
        assertNotNull(locality, "Конвертация объекта LocalityDto должна возвращать не-null объект Locality");
        assertEquals(localityDto.getSettlement(), locality.getSettlement(), "Неверное значение для поля settlement");
        assertEquals(localityDto.getRegion(), locality.getRegion(), "Неверное значение для поля region");
        assertEquals(localityDto.getWidth(), locality.getWidth(), "Неверное значение для поля width");
        assertEquals(localityDto.getHeight(), locality.getHeight(), "Неверное значение для поля height");
    }

}