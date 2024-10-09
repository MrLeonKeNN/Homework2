package com.homework2.service.impl;

import com.homework2.converters.impl.LocalityConverter;
import com.homework2.dto.LocalityDto;
import com.homework2.exeptions.EntityNotFoundException;
import com.homework2.models.Locality;
import com.homework2.repository.impl.LocalityRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LocalityServiceImplTest {

    @Mock
    private LocalityRepositoryImpl localityDao;

    @Mock
    private LocalityConverter converter;

    @InjectMocks
    private LocalityServiceImpl localityService;

    @Test
    public void getById_success() {
//        Locality locality = new Locality();
//        locality.setId(1L);
//        locality.setName("Test Locality");
//
//        LocalityDto localityDto = new LocalityDto();
//        localityDto.setId(1L);
//        localityDto.setName("Test Locality");
//
//        when(localityDao.findById(1L)).thenReturn(Optional.of(locality));
//        when(converter.toDto(locality)).thenReturn(localityDto);
//
//        LocalityDto result = localityService.getById(1L);
//
//        assertEquals(localityDto.getId(), result.getId());
//        assertEquals(localityDto.getName(), result.getName());
//
//        verify(localityDao, times(1)).findById(1L);
//        verify(converter, times(1)).toDto(locality);
    }

    @Test
    public void getById_notFound() {
        when(localityDao.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> localityService.getById(1L));

        verify(localityDao, times(1)).findById(1L);
        verify(converter, never()).toDto(any(Locality.class));
    }

    @Test
    public void save_success() {
        LocalityDto localityDto = new LocalityDto();
//        localityDto.setId(1L);
        localityDto.setRegion("Test Locality");

        Locality locality = new Locality();
        locality.setId(1L);
        locality.setRegion("Test Locality");

        when(converter.fromDto(localityDto)).thenReturn(locality);

        localityService.save(localityDto);

        verify(converter, times(1)).fromDto(localityDto);
        verify(localityDao, times(1)).save(locality);
    }

}