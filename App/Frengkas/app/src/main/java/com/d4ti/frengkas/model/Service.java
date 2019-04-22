package com.d4ti.frengkas.model;

import com.google.gson.annotations.SerializedName;

public class Service {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("desc")
    private String desc;

    public Service() {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
