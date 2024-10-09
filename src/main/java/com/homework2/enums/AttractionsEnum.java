package com.homework2.enums;

public enum AttractionsEnum {

    PALACES("Palaces"), PARKS("Parks"), MUSEUMS("Museums"), ARCHAEOLOGICAL_SITES("Archaeological sites"), NATURE("Nature"), RESERVES("Reserves");

    public final String meaning;

    AttractionsEnum(String meaning) {
        this.meaning = meaning;
    }
}
