package com.homework2.service.impl;

import com.homework2.converters.api.Converter;
import com.homework2.dto.AttractionDto;
import com.homework2.enums.AttractionNames;
import com.homework2.enums.AttractionsEnum;
import com.homework2.exeptions.EntityNotFoundException;
import com.homework2.models.Assistance;
import com.homework2.models.Attraction;
import com.homework2.models.Locality;
import com.homework2.repository.impl.AttractionRepositoryImpl;
import com.homework2.repository.impl.LocalityRepositoryImpl;
import com.homework2.service.api.AttractionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepositoryImpl attractionRepository;
    private final Converter<Attraction, AttractionDto> converter;
    private final LocalityRepositoryImpl localityRepository;

    private static final String  LOCALITY_NOT_FOUND = "Locality not found";
    private static final String  ATTRACTION_NOT_FOUND = "Attraction not found";


    /**
     * Saves a new attraction.
     *
     * @param attractionDto the DTO object representing the attraction.
     * @throws EntityNotFoundException if the specified locality is not found.
     */
    @Transactional
    @Override
    public void save(AttractionDto attractionDto) {
        Locality locality = localityRepository.findById(attractionDto.getLocalityIndex())
                .orElseThrow(() -> new EntityNotFoundException(LOCALITY_NOT_FOUND));
        Attraction attraction = converter.fromDto(attractionDto);
        attraction.setLocality(locality);
        attractionRepository.save(attraction);
    }

    /**
     * Deletes an attraction by the specified ID.
     *
     * @param id the identifier of the attraction to delete.
     * @throws EntityNotFoundException if the attraction with the specified ID is not found.
     */
    @Override
    @Transactional
    public void delete(Long id) {
        Attraction attraction = attractionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ATTRACTION_NOT_FOUND));

        if (attraction.getAssistances() != null) {
            for (Assistance assistance : attraction.getAssistances()) {
                assistance.setAttractions(null);
            }
        }
        attraction.setAssistances(null);
        attractionRepository.delete(attraction);
    }

    /**
     * Retrieves a list of attractions by the specified locality.
     *
     * @param id the identifier of the locality.
     * @return a list of DTO objects representing the attractions in the specified locality.
     * @throws EntityNotFoundException if the locality with the specified ID is not found.
     */
    @Override
    @Transactional
    public List<AttractionDto> getByLocality(Long id) {
        return converter.toDto(localityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(LOCALITY_NOT_FOUND)).getAttractions());
    }

    /**
     * Updates information about an attraction.
     *
     * @param id the identifier of the attraction to update.
     * @param attractionDto the DTO object containing new data.
     * @throws EntityNotFoundException if the attraction with the specified ID is not found.
     */
    @Override
    @Transactional
    public void edit(Long id, AttractionDto attractionDto) {
        Attraction attraction = attractionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ATTRACTION_NOT_FOUND));
        attraction.setShortDescription(attractionDto.getShortDescription());
        attraction.setName(attractionDto.getName());
        attractionRepository.edit(attraction);
    }

    /**
     * Retrieves a list of attractions sorted by name and type.
     *
     * @param attractionName the name for sorting.
     * @param attractionsEnum the enumeration of attraction types for filtering.
     * @return a list of DTO objects representing the sorted attractions.
     */
    @Override
    @Transactional
    public List<AttractionDto> getBySort(AttractionNames attractionName, AttractionsEnum attractionsEnum) {
        return converter.toDto(attractionRepository.getBySort(attractionsEnum, attractionName));
    }
}
