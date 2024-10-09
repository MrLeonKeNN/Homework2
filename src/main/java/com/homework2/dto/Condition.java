package com.homework2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Condition {
    private String text;
    private String icon;
    private Integer code;
}
