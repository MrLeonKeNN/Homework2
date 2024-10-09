package com.homework2.service.impl;

import com.homework2.converters.impl.LocalityConverter;
import com.homework2.dto.LocalityDto;
import com.homework2.exeptions.EntityNotFoundException;
import com.homework2.repository.impl.LocalityRepositoryImpl;
import com.homework2.service.api.LocalityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the {@link LocalityService} interface for managing locality operations.
 */
@AllArgsConstructor
@Service
public class LocalityServiceImpl implements LocalityService {

    private final LocalityRepositoryImpl localityDao;
    private final LocalityConverter converter;

    private static final String  LOCALITY_NOT_FOUND = "Locality not found";

    /**
     * Retrieves a locality by its identifier.
     *
     * @param id the identifier of the locality to be retrieved
     * @return the LocalityDto representing the found locality
     * @throws EntityNotFoundException if the locality with the given id is not found
     */
    @Transactional
    @Override
    public LocalityDto getById(Long id) {
        return converter.toDto(localityDao.findById(id).orElseThrow(() -> new EntityNotFoundException(LOCALITY_NOT_FOUND)));
    }

    /**
     * Saves a new locality.
     *
     * @param localityDto the LocalityDto to be saved
     */
    @Transactional
    @Override
    public void save(LocalityDto localityDto) {
        localityDao.save(converter.fromDto(localityDto));
    }
}
