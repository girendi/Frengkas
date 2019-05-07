package com.d4ti.frengkas.admin.service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Category;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCategoryActivity extends AppCompatActivity {

    private EditText et_name, et_price;
    private Button btn_create;
    private BaseService baseService;
    private int id_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Create Category");

        initComponent();

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCategory();
            }
        });
    }

    private void createCategory() {
        baseService.createCategory(id_service, et_name.getText().toString(), Double.parseDouble(et_price.getText().toString())).enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.isSuccessful()){
                    Toast.makeText(CreateCategoryActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initComponent() {
        id_service = getIntent().getIntExtra("ID_SERVICE", 0);
        baseService = APIUtils.getApiService();
        et_name = findViewById(R.id.et_category_name);
        et_price = findViewById(R.id.et_category_price);
        btn_create = findViewById(R.id.btn_create);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
