package com.example.gd.doctruyenhay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gd.doctruyenhay.adapter.AdapterChuong;
import com.example.gd.doctruyenhay.object.ObjChuong;
import com.example.gd.doctruyenhay.object.te;

import java.util.ArrayList;

public class DemoActivity extends AppCompatActivity {
    ArrayList<ObjChuong> listCHuong=new ArrayList<>();
    AdapterChuong adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        adapter=new AdapterChuong(getApplicationContext(),android.R.layout.simple_list_item_1,listCHuong);

        te lv=(te) findViewById(R.id.lvdemo);
        lv.setAdapter(adapter);

//        });
    }


}
