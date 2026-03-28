package com.epam.training.iryna_chekh;

public enum SortingParameter {
    TITLE_ASC("az"), TITLE_DES("za"),PRICE_ASC("lohi"), PRICE_DES("hilo");
    private final String value;

    SortingParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
