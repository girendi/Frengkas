package com.d4ti.frengkas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.d4ti.frengkas.adapter.ServiceAdapter;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.fragment.BookingFragment;
import com.d4ti.frengkas.fragment.HomeFragment;
import com.d4ti.frengkas.model.Service;
import com.d4ti.frengkas.response.ServiceResponse;
import com.d4ti.frengkas.sharedPreference.SaveSharedPreference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageButton imb_home, imb_booking;
    HomeFragment homeFragment = new HomeFragment();
    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initComponent();
        onClick();
    }

    public void initComponent(){
        imb_booking = findViewById(R.id.btn_booking);
        imb_home = findViewById(R.id.btn_home);
    }

    public void onClick(){
        imb_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction().replace(R.id.frame_auth, homeFragment).commit();
            }
        });

        imb_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SaveSharedPreference.getLoggedStatus(getApplicationContext())){
                    BookingFragment bookingFragment = new BookingFragment();
                    manager.beginTransaction().replace(R.id.frame_auth, bookingFragment).commit();
                }else {
                    startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        manager.beginTransaction().replace(R.id.frame_auth, homeFragment).commit();
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
        getMenuInflater().inflate(R.menu.main, menu);

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
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_login){
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
