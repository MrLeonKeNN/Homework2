package com.homework2.models;

import com.homework2.enums.AttractionsEnum;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;


class AttractionTest {

    @Test
    public void testEqualsReflexive() {
        Attraction attraction = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        assertThat(attraction).isEqualTo(attraction);
    }

    @Test
    public void testEqualsSymmetric() {
        Attraction attraction1 = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        Attraction attraction2 = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        assertThat(attraction1).isEqualTo(attraction2);
        assertThat(attraction2).isEqualTo(attraction1);
    }

    @Test
    public void testEqualsTransitive() {
        Attraction attraction1 = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        Attraction attraction2 = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        Attraction attraction3 = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        assertThat(attraction1).isEqualTo(attraction2);
        assertThat(attraction2).isEqualTo(attraction3);
        assertThat(attraction1).isEqualTo(attraction3);
    }

    @Test
    public void testEqualsConsistent() {
        Attraction attraction1 = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        Attraction attraction2 = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        assertThat(attraction1).isEqualTo(attraction2);
        assertThat(attraction1).isEqualTo(attraction2); // Проверка на согласованность
    }

    @Test
    public void testEqualsNull() {
        Attraction attraction = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        assertThat(attraction).isNotEqualTo(null);
    }

    @Test
    public void testHashCodeConsistency() {
        Attraction attraction = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        int initialHashCode = attraction.hashCode();
        int repeatedHashCode = attraction.hashCode();
        assertThat(initialHashCode).isEqualTo(repeatedHashCode);
    }

    @Test
    public void testEqualsAndHashCodeContract() {
        Attraction attraction1 = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        Attraction attraction2 = Attraction.builder()
                .id(1L)
                .name("Test Attraction")
                .dateOfCreation(LocalDateTime.now())
                .typeAttraction(AttractionsEnum.PALACES)
                .build();
        assertThat(attraction1).isEqualTo(attraction2);
        assertThat(attraction1.hashCode()).isEqualTo(attraction2.hashCode());
    }

}