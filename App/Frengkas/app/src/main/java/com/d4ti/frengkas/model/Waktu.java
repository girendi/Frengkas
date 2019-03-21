package com.d4ti.frengkas.model;

import com.google.gson.annotations.SerializedName;

public class Waktu {
    @SerializedName("id")
    private int id;
    @SerializedName("date")
    private String date;

    public Waktu() {
    }

    public Waktu(int id, String date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
