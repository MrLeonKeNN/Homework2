package com.homework2.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class LocalityTest {
    @Test
    public void testEqualsReflexive() {
        Locality locality = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        assertThat(locality).isEqualTo(locality);
    }

    @Test
    public void testEqualsSymmetric() {
        Locality locality1 = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        Locality locality2 = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        assertThat(locality1).isEqualTo(locality2);
        assertThat(locality2).isEqualTo(locality1);
    }

    @Test
    public void testEqualsTransitive() {
        Locality locality1 = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        Locality locality2 = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        Locality locality3 = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        assertThat(locality1).isEqualTo(locality2);
        assertThat(locality2).isEqualTo(locality3);
        assertThat(locality1).isEqualTo(locality3);
    }

    @Test
    public void testEqualsConsistent() {
        Locality locality1 = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        Locality locality2 = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        assertThat(locality1).isEqualTo(locality2);
        assertThat(locality1).isEqualTo(locality2); // Проверка на согласованность
    }

    @Test
    public void testEqualsNull() {
        Locality locality = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        assertThat(locality).isNotEqualTo(null);
    }

    @Test
    public void testHashCodeConsistency() {
        Locality locality = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        int initialHashCode = locality.hashCode();
        int repeatedHashCode = locality.hashCode();
        assertThat(initialHashCode).isEqualTo(repeatedHashCode);
    }

    @Test
    public void testEqualsAndHashCodeContract() {
        Locality locality1 = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        Locality locality2 = Locality.builder()
                .id(1L)
                .settlement("Test Settlement")
                .region("Test Region")
                .width(12.34)
                .height(56.78)
                .build();
        assertThat(locality1).isEqualTo(locality2);
        assertThat(locality1.hashCode()).isEqualTo(locality2.hashCode());
    }

}