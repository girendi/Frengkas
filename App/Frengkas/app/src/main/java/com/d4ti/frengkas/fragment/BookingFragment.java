package com.d4ti.frengkas.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.adapter.BookingAdapter;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Order;
import com.d4ti.frengkas.response.OrderResponse;
import com.d4ti.frengkas.sharedPreference.SaveSharedPreference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment {

    private RecyclerView rv_orders;
    private List<Order> orders;
    private BaseService baseService;
    private View view;
    private int id;

    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_booking, container, false);
        initComponent();
        setBooking();
        return view;
    }

    private void setBooking() {
        baseService.getOrderCustomer(id).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()){
                    orders = response.body().getOrders();
                    if (!orders.isEmpty()){
                        rv_orders.setLayoutManager(new LinearLayoutManager(getActivity()));
                        BookingAdapter bookingAdapter = new BookingAdapter(getActivity());
                        bookingAdapter.setOrders(orders);
                        rv_orders.setAdapter(bookingAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void initComponent(){
        id = SaveSharedPreference.getIdUser(getActivity());
        rv_orders = view.findViewById(R.id.rv_booking);
        orders = new ArrayList<>();
        baseService = APIUtils.getApiService();
    }

}
