package com.d4ti.frengkas.response;

import com.d4ti.frengkas.model.Order;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderResponse {
    @SerializedName("Order")
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }
}
