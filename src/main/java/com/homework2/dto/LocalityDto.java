package com.homework2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class LocalityDto {

    @NotNull
    private String settlement;
    @NotNull
    private String region;
    private Double width;
    private Double height;
}
