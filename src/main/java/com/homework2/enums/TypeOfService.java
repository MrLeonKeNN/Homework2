package com.homework2.enums;

public enum TypeOfService {

    GUIDE("guide"), CAR_TOUR("cat tour"), MEALS("meals");

    private final String type;

    TypeOfService(String type) {
        this.type = type;
    }
}
