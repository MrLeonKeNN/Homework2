package com.homework2.repository.api;

import com.homework2.enums.AttractionNames;
import com.homework2.enums.AttractionsEnum;
import com.homework2.models.Attraction;

import java.util.List;

public interface AttractionRepository {

    void edit(Attraction attration);

    List<Attraction> getBySort(AttractionsEnum attractionsEnum, AttractionNames name);
}
