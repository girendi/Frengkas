package com.d4ti.frengkas.admin.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.adapter.CategoryAdapter;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Category;
import com.d4ti.frengkas.model.Service;
import com.d4ti.frengkas.response.CategoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailServiceActivity extends AppCompatActivity {

    private TextView tv_service, tv_desc;
    private Button btn_add;
    private RecyclerView rv_category;
    private BaseService baseService;
    private int id_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_service);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Detail Service");
        initComponent();
        setData();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateCategoryActivity.class);
                intent.putExtra("ID_SERVICE", id_service);
                startActivity(intent);
            }
        });
    }

    private void setData() {
        baseService.getDetailService(id_service).enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {
                if (response.isSuccessful()){
                    Service service = response.body();
                    tv_service.setText(service.getName());
                    tv_desc.setText(service.getDesc());
                }
            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {
                t.printStackTrace();
            }
        });

        baseService.getCategory(id_service).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()){
                    List<Category> categories = response.body().getCategories();
                    if (!categories.isEmpty()){
                        rv_category.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        CategoryAdapter categoryAdapter = new CategoryAdapter(getApplicationContext());
                        categoryAdapter.setCategories(categories);
                        rv_category.setAdapter(categoryAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initComponent() {
        baseService = APIUtils.getApiService();
        id_service = getIntent().getIntExtra("ID_SERVICE", 0);

        tv_service = findViewById(R.id.txt_service);
        tv_desc = findViewById(R.id.txt_desc_service);
        rv_category = findViewById(R.id.rv_category);
        btn_add = findViewById(R.id.btn_add);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }
}
