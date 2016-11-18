package com.example.gd.doctruyenhay;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gd.doctruyenhay.adapter.AdapterChuong;
import com.example.gd.doctruyenhay.database.SqliteDAO;
import com.example.gd.doctruyenhay.object.ObjChuong;
import com.example.gd.doctruyenhay.object.ObjTruyen;

import java.util.ArrayList;
import java.util.List;

public class DsChuongActivity extends AppCompatActivity {
    ArrayList<ObjChuong> listCHuong=new ArrayList<>();
    AdapterChuong adapter;
    SqliteDAO database;

    ObjTruyen mTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_chuong);
        setActionBar();
        database=new SqliteDAO(getApplicationContext());
        mTruyen=(ObjTruyen) getIntent().getSerializableExtra("truyen");



        listCHuong= database.getChongTruyen(mTruyen.id);
        adapter=new AdapterChuong(getApplicationContext(),android.R.layout.simple_list_item_1,listCHuong);
        ListView lv=(ListView) findViewById(R.id.lvChuong);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(),DocTruyenActivity.class));
            }
        });
    }

    private void setActionBar(){
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
