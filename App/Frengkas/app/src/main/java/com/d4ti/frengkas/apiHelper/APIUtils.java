package com.d4ti.frengkas.apiHelper;

public class APIUtils {
    public static final String API_URL = "http://192.168.43.194/Frengkas/Web/Frengkas/public/api/";

    public static BaseService getApiService(){
        return RetrofitClient.getClient(API_URL).create(BaseService.class);
    }
}
