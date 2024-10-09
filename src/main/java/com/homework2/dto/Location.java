package com.homework2.dto;

import lombok.Data;

@Data
public class Location {
    private String name;
    private String region;
    private String country;
    private Double lat;
    private Double lon;
    private String tzId;
    private Integer localtimeEpoch;
    private String localtime;
}
