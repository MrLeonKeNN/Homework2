package com.homework2.service.api;

import com.homework2.dto.LocalityDto;

public interface LocalityService {

    LocalityDto getById(Long id);

    void save(LocalityDto localityDto);
}
