package com.d4ti.frengkas.apiHelper;

public class APIUtils {
    public static final String API_URL = "http://frengkas.semuada.id/index.php/api/";

    public static BaseService getApiService(){
        return RetrofitClient.getClient(API_URL).create(BaseService.class);
    }
}
