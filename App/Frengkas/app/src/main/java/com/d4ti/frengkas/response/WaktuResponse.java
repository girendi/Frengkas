package com.d4ti.frengkas.response;

import com.d4ti.frengkas.model.Waktu;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WaktuResponse {
    @SerializedName("Waktu")
    private List<Waktu> waktus;

    public List<Waktu> getWaktus() {
        return waktus;
    }
}
