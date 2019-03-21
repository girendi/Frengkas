package com.d4ti.frengkas.response;

import com.d4ti.frengkas.model.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("Category")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }
}
