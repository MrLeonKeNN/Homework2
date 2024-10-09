package com.homework2.repository.impl;

import com.homework2.enums.AttractionNames;
import com.homework2.enums.AttractionsEnum;
import com.homework2.models.Attraction;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@ContextConfiguration(classes = {TestHibernateConfig.class})
@Transactional
class AttractionRepositoryImplTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @Autowired
    private AttractionRepositoryImpl attractionRepository;

    @Autowired
    private SessionFactory sessionFactory;

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }


    @BeforeEach
    void setUp() {
        clearDatabase();
        Attraction attraction1 = new Attraction();
        attraction1.setName("Аттракцион 1");
        attraction1.setTypeAttraction(AttractionsEnum.MUSEUMS);
        attractionRepository.save(attraction1);

        Attraction attraction2 = new Attraction();
        attraction2.setName("Аттракцион 2");
        attraction2.setTypeAttraction(AttractionsEnum.MUSEUMS);
        attractionRepository.save(attraction2);
    }

    private void clearDatabase() {
        Query deleteQuery = sessionFactory.getCurrentSession().createQuery(("DELETE FROM Attraction"));
        deleteQuery.executeUpdate();

        sessionFactory.getCurrentSession().createNativeQuery("ALTER SEQUENCE attraction_id_seq  RESTART WITH 1").executeUpdate();
    }

    @Test
    void testGetBySort() {
        List<Attraction> results = attractionRepository.getBySort(AttractionsEnum.MUSEUMS, AttractionNames.NAME);

        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(2);
        assertThat(results.get(0).getName()).isEqualTo("Аттракцион 1");
        assertThat(results.get(1).getName()).isEqualTo("Аттракцион 2");
    }

    @Test
    void testEdit() {
        Attraction attraction = attractionRepository.findById(1L).orElse(null);
        attraction.setName("Измененный Аттракцион");
        attractionRepository.edit(attraction);

        Attraction updatedAttraction = attractionRepository.findById(1L).orElseThrow();
        assertThat(updatedAttraction.getName()).isEqualTo("Измененный Аттракцион");
    }

    @Test
    void testFindById() {
        Attraction attraction = attractionRepository.findById(1L).orElse(null);
        assertThat(attraction).isNotNull();
        assertThat(attraction.getName()).isEqualTo("Аттракцион 1");
    }

    @Test
    void testDeleteById() {
        attractionRepository.deleteById(8L);

        Attraction attraction = attractionRepository.findById(8L).orElse(null);
        assertThat(attraction).isNull();
    }

    @Test
    void testDeleteByObject() {
        Attraction attraction = attractionRepository.findById(1L).orElse(null);
        assertThat(attraction).isNotNull();

        attractionRepository.delete(attraction);

        Attraction expected = attractionRepository.findById(1L).orElse(null);
        assertThat(expected).isNull();
    }

    @Test
    void testFindAll() {
        List<Attraction> attractions = attractionRepository.findAll();
        assertThat(attractions).hasSize(2);
    }

}