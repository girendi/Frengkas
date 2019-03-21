package com.d4ti.frengkas.apiHelper;

import com.d4ti.frengkas.model.Order;
import com.d4ti.frengkas.model.Pukul;
import com.d4ti.frengkas.model.Service;
import com.d4ti.frengkas.model.User;
import com.d4ti.frengkas.model.Waktu;
import com.d4ti.frengkas.response.CategoryResponse;
import com.d4ti.frengkas.response.OrderResponse;
import com.d4ti.frengkas.response.PukulResponse;
import com.d4ti.frengkas.response.ServiceResponse;
import com.d4ti.frengkas.response.WaktuResponse;

import retrofit2.Call;
import retrofit2.http.Field;
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
    Call<Service> createService(@Field("name") String name);

    @GET("category/{id_service}")
    Call<CategoryResponse> getCategory(@Path("id_service") int id_service);

    @FormUrlEncoded
    @POST("login")
    Call<User> loginRequest(@Field("email") String email,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<User> registerRequest(@Field("name") String name,
                               @Field("email") String email,
                               @Field("password") String password);

    @GET("waktu")
    Call<WaktuResponse> getWaktu();

    @GET("show/waktu/{id}")
    Call<Waktu> getDetailWaktu(@Path("id") int id);

    @FormUrlEncoded
    @POST("waktu")
    Call<Waktu> createWaktu(@Field("date") String date);

    @GET("pukul/{id_waktu}")
    Call<PukulResponse> getPukul(@Path("id_waktu") int id_waktu);

    @GET("show/pukul/{id}")
    Call<Pukul> getDetailPukul(@Path("id") int id);

    @FormUrlEncoded
    @POST("pukul")
    Call<Pukul> createPukul(@Field("id_waktu") int id_waktu,
                            @Field("start") String start,
                            @Field("end") String end);

    @GET("order")
    Call<OrderResponse> getOrder();

    @GET("show/order/{id}")
    Call<Order> getDetailOrder(@Path("id") int id);

    @FormUrlEncoded
    @POST("order")
    Call<Order> createOrder(@Field("id_user") int id_user,
                            @Field("id_pukul") int id_pukul,
                            @Field("id_category") int id_category,
                            @Field("location") String location);

}
