package com.homework2.service.impl;

import com.homework2.converters.api.Converter;
import com.homework2.dto.AttractionDto;
import com.homework2.exeptions.EntityNotFoundException;
import com.homework2.models.Attraction;
import com.homework2.models.Locality;
import com.homework2.repository.impl.AttractionRepositoryImpl;
import com.homework2.repository.impl.LocalityRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AttractionServiceImplTest {

    @Mock
    private AttractionRepositoryImpl attractionRepository;

    @Mock
    private Converter<Attraction, AttractionDto> converter;

    @Mock
    private LocalityRepositoryImpl localityRepository;

    @InjectMocks
    private AttractionServiceImpl attractionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_Positive() {
        AttractionDto attractionDto = new AttractionDto();
        Attraction attraction = Attraction.builder().name("name").build();
        Locality locality = Locality.builder().id(11L).build();
        Optional<Locality> optional = Optional.ofNullable(locality);

        when(localityRepository.findById(any())).thenReturn(optional);
        when(converter.fromDto(attractionDto)).thenReturn(attraction);
        doNothing().when(attractionRepository).save(any(Attraction.class));
        attractionService.save(attractionDto);
        verify(attractionRepository, times(1)).save(attraction);
    }

    @Test
    void testSave_Negative() {
        AttractionDto attractionDto = new AttractionDto();
        when(converter.fromDto(attractionDto)).thenThrow(new RuntimeException("Conversion error"));
        assertThrows(RuntimeException.class, () -> attractionService.save(attractionDto));
    }

    @Test
    void testDelete_Positive() {
        Long id = 1L;
        Attraction attraction = Attraction.builder().name("name").build();
        Optional<Attraction> optional = Optional.ofNullable(attraction);

        when(attractionRepository.findById(any())).thenReturn(optional);
        doNothing().when(attractionRepository).delete(attraction);
        attractionService.delete(id);
        verify(attractionRepository, times(1)).delete(attraction);
    }

    @Test
    void testDelete_Negative() {
        Long id = 1L;
        doThrow(new EntityNotFoundException("Attraction not found")).when(attractionRepository).deleteById(id);
        assertThrows(EntityNotFoundException.class, () -> attractionService.delete(id));
    }

    @Test
    void testGetByLocality_Positive() {
        Long localityId = 1L;
        Attraction attraction = new Attraction();
        List<Attraction> attractions = List.of(attraction);
        Locality locality = Locality.builder().attractions(attractions).build();
        when(localityRepository.findById(localityId)).thenReturn(Optional.of(locality));
        when(converter.toDto(attractions)).thenReturn(List.of(new AttractionDto()));
        List<AttractionDto> result = attractionService.getByLocality(localityId);
        assertNotNull(result);
//        assertEquals(result,attractions);
        verify(localityRepository, times(1)).findById(localityId);
        verify(converter, times(1)).toDto(attractions);
    }

    @Test
    void testGetByLocality_Negative() {
        Long localityId = 1L;
        when(localityRepository.findById(localityId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> attractionService.getByLocality(localityId));
    }

    @Test
    void testEdit_Positive() {
        Long attractionId = 1L;
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setShortDescription("Updated description");
        Attraction attraction = new Attraction();
        when(attractionRepository.findById(attractionId)).thenReturn(Optional.of(attraction));
        doNothing().when(attractionRepository).edit(any(Attraction.class));
        attractionService.edit(attractionId, attractionDto);
//        verify(attraction).setShortDescription("Updated description");
        verify(attractionRepository, times(1)).edit(attraction);
    }

    @Test
    void testEdit_Negative() {
        Long attractionId = 1L;
        AttractionDto attractionDto = new AttractionDto();
        when(attractionRepository.findById(attractionId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> attractionService.edit(attractionId, attractionDto));
    }
}