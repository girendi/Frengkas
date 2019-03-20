package com.d4ti.frengkas.apiHelper;

import com.d4ti.frengkas.model.Service;
import com.d4ti.frengkas.response.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseService {

    @GET("service")
    Call<ServiceResponse> getService();

    @GET("service/{id}")
    Call<Service> getDetailService(@Path("id") int id);

    @FormUrlEncoded
    @POST("service")
    Call<Service> createService(@Body Service service);

}
