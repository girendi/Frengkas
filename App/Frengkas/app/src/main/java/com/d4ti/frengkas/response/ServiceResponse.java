package com.d4ti.frengkas.response;

import com.d4ti.frengkas.model.Service;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceResponse {
    @SerializedName("Service")
    private List<Service> services;

    public List<Service> getServices() {
        return services;
    }
}
