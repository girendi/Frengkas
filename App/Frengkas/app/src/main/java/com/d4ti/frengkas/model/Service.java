package com.d4ti.frengkas.model;

import com.google.gson.annotations.SerializedName;

public class Service {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public Service() {
    }

    public Service(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
