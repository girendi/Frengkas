package com.d4ti.frengkas.response;

import com.d4ti.frengkas.model.Pukul;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PukulResponse {
    @SerializedName("Pukul")
    private List<Pukul> pukuls;

    public List<Pukul> getPukuls() {
        return pukuls;
    }
}
