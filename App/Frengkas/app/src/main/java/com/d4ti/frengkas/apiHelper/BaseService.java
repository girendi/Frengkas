package com.d4ti.frengkas.apiHelper;

import com.d4ti.frengkas.model.Category;
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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseService {

    //Service
    @GET("service")
    Call<ServiceResponse> getService();

    @GET("service/{id}")
    Call<Service> getDetailService(@Path("id") int id);

    @FormUrlEncoded
    @POST("service")
    Call<Service> createService(@Field("name") String name,
                                @Field("desc") String desc);

    @FormUrlEncoded
    @POST("service/{id}")
    Call<Service> updateService(@Path("id") int id,
                                @Field("name") String name,
                                @Field("desc") String desc);

    @POST("delete/service/{id}")
    Call<ResponseBody> deleteService(@Path("id") int id);

    //Category
    @GET("category/{id_service}")
    Call<CategoryResponse> getCategory(@Path("id_service") int id_service);

    @GET("show/category/{id}")
    Call<Category> getDetailCategory(@Path("id") int id);

    @FormUrlEncoded
    @POST("category")
    Call<Category> createCategory(@Field("id_service") int id_service,
                                  @Field("name") String name,
                                  @Field("price") Double price);

    @FormUrlEncoded
    @POST("category/{id}")
    Call<Category> updateCategory(@Path("id") int id,
                                  @Field("name") String name,
                                  @Field("price") Double price);

    @POST("delete/category/{id}")
    Call<ResponseBody> deleteCategory(@Path("id") int id);

    //Auth
    @GET("user/{id}")
    Call<User> getDetailUser(@Path("id") int id);

    @FormUrlEncoded
    @POST("login")
    Call<User> loginRequest(@Field("email") String email,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<User> registerRequest(@Field("name") String name,
                               @Field("email") String email,
                               @Field("password") String password,
                               @Field("alamat") String alamat,
                               @Field("no_telp") String no_telp);

    //Waktu
    @GET("waktu")
    Call<WaktuResponse> getWaktu();

    @GET("show/waktu/{id}")
    Call<Waktu> getDetailWaktu(@Path("id") int id);

    @FormUrlEncoded
    @POST("waktu")
    Call<Waktu> createWaktu(@Field("date") String date);

    @FormUrlEncoded
    @POST("waktu/{id}")
    Call<Waktu> updateWaktu(@Path("id") int id,
                            @Field("date") String date);

    @POST("delete/waktu/{id}")
    Call<ResponseBody> deleteWaktu(@Path("id") int id);

    //Pukul
    @GET("pukul/{id_waktu}")
    Call<PukulResponse> getPukul(@Path("id_waktu") int id_waktu);

    @GET("show/pukul/{id}")
    Call<Pukul> getDetailPukul(@Path("id") int id);

    @FormUrlEncoded
    @POST("pukul")
    Call<Pukul> createPukul(@Field("id_waktu") int id_waktu,
                            @Field("start") String start,
                            @Field("end") String end);

    @FormUrlEncoded
    @POST("pukul/{id}")
    Call<Pukul> updatePukul(@Path("id") int id,
                            @Field("start") String start,
                            @Field("end") String end);

    @POST("delete/pukul/{id}")
    Call<ResponseBody> deletePukul(@Path("id") int id);


    //Order
    @GET("order")
    Call<OrderResponse> getOrder();

    @GET("orderCustomer/{id}")
    Call<OrderResponse> getOrderCustomer(@Path("id") int id);

    @GET("show/order/{id}")
    Call<Order> getDetailOrder(@Path("id") int id);

    @FormUrlEncoded
    @POST("order")
    Call<Order> createOrder(@Field("id_user") int id_user,
                            @Field("id_pukul") int id_pukul,
                            @Field("id_category") int id_category,
                            @Field("location") String location);

    @FormUrlEncoded
    @POST("order/{id}")
    Call<Order> updateOrder(@Path("id") int id,
                            @Field("status") String status);

    @POST("delete/order/{id}")
    Call<ResponseBody> deleteOrder(@Path("id") int id);

}
