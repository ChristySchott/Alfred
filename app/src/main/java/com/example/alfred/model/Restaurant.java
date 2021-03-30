package com.example.alfred.model;

import java.io.Serializable;

public class Restaurant implements Serializable {

    // TODO - VARIÁVEL DA IMAGEM
    private String name;
    private Integer rating;
    private String category;
    private Double averagePrice;

    public Restaurant(String name, Integer rating, String category, double averagePrice) {
        this.name = name;
        this.rating = rating;
        this.category = category;
        this.averagePrice = averagePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }
}
