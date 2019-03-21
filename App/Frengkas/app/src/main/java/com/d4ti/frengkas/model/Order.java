package com.d4ti.frengkas.model;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("id")
    private int id;
    @SerializedName("id_user")
    private int id_user;
    @SerializedName("id_pukul")
    private int id_pukul;
    @SerializedName("id_category")
    private int id_category;
    @SerializedName("location")
    private String location;

    public Order() {
    }

    public Order(int id, int id_user, int id_pukul, int id_category, String location) {
        this.id = id;
        this.id_user = id_user;
        this.id_pukul = id_pukul;
        this.id_category = id_category;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_pukul() {
        return id_pukul;
    }

    public void setId_pukul(int id_pukul) {
        this.id_pukul = id_pukul;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
