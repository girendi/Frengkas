package com.d4ti.frengkas.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.adapter.ServiceAdapter;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Service;
import com.d4ti.frengkas.response.ServiceResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView rv_service;
    private List<Service> services;
    private BaseService baseService;
    private View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponent();
        getService();
        return view;
    }

    public void initComponent(){
        rv_service = view.findViewById(R.id.rv_service);
        services = new ArrayList<>();
        baseService = APIUtils.getApiService();
    }

    public void getService(){
        baseService.getService().enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                if (response.isSuccessful()){
                    services = response.body().getServices();
                    if (!services.isEmpty()){
                        rv_service.setLayoutManager(new LinearLayoutManager(getActivity()));
                        ServiceAdapter serviceAdapter = new ServiceAdapter(getActivity());
                        serviceAdapter.setServices(services);
                        rv_service.setAdapter(serviceAdapter);
                        Log.i("Service Name", services.get(0).getName());
                    }else {
                        Log.i("Service Data", "Data Empty");
                    }
                }else{
                    Log.e("Service Error", response.message());
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Log.e("Api Error", "Cant load data base");
            }
        });
    }

}
