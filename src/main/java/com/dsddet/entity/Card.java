package com.dsddet.entity;

public final class Card {
    private final String label;
    private final Integer value;
    private final String type;


    public Card(String label, String type, Integer value) {
        this.label = label;
        this.value = value;
        this.type = type;
    }

    public String getLabel() {
        return this.label;
    }

    public Integer getValue() {
        return this.value;
    }


    @Override
    public String toString() {
        return String.format("%s %s", this.label, this.type);
    }
}