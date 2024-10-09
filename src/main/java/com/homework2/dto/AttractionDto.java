package com.homework2.dto;

import com.homework2.enums.AttractionsEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AttractionDto  {

    private Long attractionId;
    private String name;
    private String shortDescription;
    private AttractionsEnum typeAttraction;


    private Long localityIndex;

}
