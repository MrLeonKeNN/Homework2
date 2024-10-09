package com.homework2.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class AssistanceTest {


    @Test
    void testEqualsReflexive() {
        Assistance assistance = Assistance.builder().id(1L).description("Test").build();
        assertThat(assistance).isEqualTo(assistance);
    }

    @Test
    void testEqualsSymmetric() {
        Assistance assistance1 = Assistance.builder().id(1L).description("Test").build();
        Assistance assistance2 = Assistance.builder().id(1L).description("Test").build();
        assertThat(assistance1).isEqualTo(assistance2);
        assertThat(assistance2).isEqualTo(assistance1);
    }

    @Test
    void testEqualsTransitive() {
        Assistance assistance1 = Assistance.builder().id(1L).description("Test").build();
        Assistance assistance2 = Assistance.builder().id(1L).description("Test").build();
        Assistance assistance3 = Assistance.builder().id(1L).description("Test").build();
        assertThat(assistance1).isEqualTo(assistance2);
        assertThat(assistance2).isEqualTo(assistance3);
        assertThat(assistance1).isEqualTo(assistance3);
    }

    @Test
    void testEqualsConsistent() {
        Assistance assistance1 = Assistance.builder().id(1L).description("Test").build();
        Assistance assistance2 = Assistance.builder().id(1L).description("Test").build();
        assertThat(assistance1).isEqualTo(assistance2);
        assertThat(assistance1).isEqualTo(assistance2);
    }

    @Test
    void testEqualsNull() {
        Assistance assistance = Assistance.builder().id(1L).description("Test").build();
        assertThat(assistance).isNotEqualTo(null);
    }

    @Test
    void testHashCodeConsistency() {
        Assistance assistance = Assistance.builder().id(1L).description("Test").build();
        int initialHashCode = assistance.hashCode();
        int repeatedHashCode = assistance.hashCode();
        assertThat(initialHashCode).isEqualTo(repeatedHashCode);
    }

    @Test
    void testEqualsAndHashCodeContract() {
        Assistance assistance1 = Assistance.builder().id(1L).description("Test").build();
        Assistance assistance2 = Assistance.builder().id(1L).description("Test").build();
        assertThat(assistance1).isEqualTo(assistance2);
        assertThat(assistance1.hashCode()).isEqualTo(assistance2.hashCode());
    }
}