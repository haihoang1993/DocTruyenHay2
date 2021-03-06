package com.example.gd.doctruyenhay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gd.doctruyenhay.adapter.AdapterTruyen;
import com.example.gd.doctruyenhay.database.SqliteDAO;
import com.example.gd.doctruyenhay.database.SqliteDatabaseCallBack;
import com.example.gd.doctruyenhay.object.ObjTruyen;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SqliteDatabaseCallBack {
    ListView lvTruyen;
    AdapterTruyen adapter;
    ArrayList<ObjTruyen> listTruyen=new ArrayList<>();

    SqliteDAO database;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        database=new SqliteDAO(getApplicationContext());
        database.setCallback(this);
        getTruyen("nt");
    }

    private void init(){
        initAppBar();
        lvTruyen=(ListView) findViewById(R.id.lvTruyen);
        adapter=new AdapterTruyen(getApplicationContext(),R.layout.item_truyen,listTruyen);
        Log.d("aaa",listTruyen.toString());
        lvTruyen.setAdapter(adapter);
        lvTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(getApplicationContext(),GioithieuTruyenActivity.class);
                Log.d("truyen",listTruyen.get(i).toString());
                intent.putExtra("truyen",listTruyen.get(i));
                startActivity(intent);
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

    private void getTruyen(String idLoai) {
        listTruyen.clear();
        database.getDanhSachTruyen(idLoai);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item_truyen clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item_truyen clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_ngon_tinh) {
            // Handle the camera action
            getTruyen("nt");
        } else if (id == R.id.menu_kiem_hiep) {
            getTruyen("kh");
        } else if (id == R.id.menu_tien_hiep) {
            getTruyen("th");
        } else if (id == R.id.menu_vong_du) {
            getTruyen("vd");
        } else if (id == R.id.menu_yeuthich) {
            startActivity(new Intent(getApplicationContext(), ListYeuThichActivity.class));
        }
// else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setNameBar(String st){
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(st);
    }

    private void initAppBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Đọc Truyện Hay");
    }

    @Override
    public void showTruyen(ObjTruyen objTruyen) {
        listTruyen.add(objTruyen);
        adapter.notifyDataSetChanged();
    }

}
