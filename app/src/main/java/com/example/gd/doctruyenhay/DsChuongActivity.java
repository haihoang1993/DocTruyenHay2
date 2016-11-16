package com.example.gd.doctruyenhay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gd.doctruyenhay.adapter.AdapterChuong;
import com.example.gd.doctruyenhay.object.ObjChuong;

import java.util.ArrayList;
import java.util.List;

public class DsChuongActivity extends AppCompatActivity {
    ArrayList<ObjChuong> listCHuong=new ArrayList<>();
    AdapterChuong adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_chuong);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setData();
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
    public void setData(){
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));
        listCHuong.add(new ObjChuong(1,"1"));

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
