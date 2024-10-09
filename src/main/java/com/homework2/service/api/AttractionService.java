package com.homework2.service.api;

import com.homework2.dto.AttractionDto;
import com.homework2.enums.AttractionNames;
import com.homework2.enums.AttractionsEnum;

import java.util.List;

public interface AttractionService {

    void save(AttractionDto attractionDto);

    void delete(Long id);

    List<AttractionDto> getByLocality(Long id);

    void edit(Long id, AttractionDto attractionDto);

    List<AttractionDto> getBySort(AttractionNames name, AttractionsEnum attractionsEnum);
}
