package com.d4ti.frengkas.apiHelper;

public class APIUtils {
    public static final String API_URL = "http://127.0.0.1:8000/api/";

    public static BaseService getApiService(){
        return RetrofitClient.getClient(API_URL).create(BaseService.class);
    }
}
