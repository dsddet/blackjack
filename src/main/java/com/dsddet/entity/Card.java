package com.dsddet.entity;

public class Card {
    private String label;
    private Integer value;
    private String type;

    public Card(){

    }

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

    public void setLabel(String label){
        this.label=label;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.label, this.type);
    }
}