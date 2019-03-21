package com.d4ti.frengkas.model;

import com.google.gson.annotations.SerializedName;

public class Pukul {
    @SerializedName("id")
    private int id;
    @SerializedName("id_waktu")
    private int id_waktu;
    @SerializedName("start")
    private String start;
    @SerializedName("end")
    private String end;
    @SerializedName("status")
    private String status;

    public Pukul() {
    }

    public Pukul(int id, int id_waktu, String start, String end, String status) {
        this.id = id;
        this.id_waktu = id_waktu;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_waktu() {
        return id_waktu;
    }

    public void setId_waktu(int id_waktu) {
        this.id_waktu = id_waktu;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
