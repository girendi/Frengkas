package com.d4ti.frengkas.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.d4ti.frengkas.AuthActivity;
import com.d4ti.frengkas.MainActivity;
import com.d4ti.frengkas.R;
import com.d4ti.frengkas.adapter.BookingAdapter;
import com.d4ti.frengkas.admin.service.ServiceActivity;
import com.d4ti.frengkas.admin.time.DateTimeActivity;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Order;
import com.d4ti.frengkas.response.OrderResponse;
import com.d4ti.frengkas.sharedPreference.SaveSharedPreference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rv_order;
    private BaseService baseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        baseService = APIUtils.getApiService();
        rv_order = findViewById(R.id.rv_order);
        setOrders();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setOrders();
    }

    private void setOrders() {
        baseService.getOrder().enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()){
                    List<Order> orders = response.body().getOrders();
                    if (!orders.isEmpty()){
                        rv_order.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        BookingAdapter bookingAdapter = new BookingAdapter(getApplicationContext());
                        bookingAdapter.setOrders(orders);
                        rv_order.setAdapter(bookingAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);

        MenuItem itemLogin = menu.findItem(R.id.action_login);
        MenuItem itemLogout = menu.findItem(R.id.action_logout);

        if (SaveSharedPreference.getLoggedStatus(getApplicationContext())){
            itemLogin.setVisible(false);
            itemLogout.setVisible(true);
        }else {
            itemLogin.setVisible(true);
            itemLogout.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login){
            //Toast.makeText(this, "Status " + SaveSharedPreference.getLoggedStatus(this), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
            finish();
        }else if (id == R.id.action_logout){
            SaveSharedPreference.setLoggedIn(getApplicationContext(), false);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            //Toast.makeText(this, "Status " + SaveSharedPreference.getLoggedStatus(this), Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_slideshow) {
            startActivity(new Intent(getApplicationContext(), ServiceActivity.class));
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(getApplicationContext(), DateTimeActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
