package com.d4ti.frengkas.model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("id")
    private int id;
    @SerializedName("id_service")
    private int id_service;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private double price;

    public Category() {
    }

    public Category(int id, int id_service, String name, double price) {
        this.id = id;
        this.id_service = id_service;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
