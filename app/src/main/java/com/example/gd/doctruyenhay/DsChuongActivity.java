package com.example.gd.doctruyenhay;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gd.doctruyenhay.adapter.AdapterChuong;
import com.example.gd.doctruyenhay.database.SqliteDAO;
import com.example.gd.doctruyenhay.object.ObjChuong;
import com.example.gd.doctruyenhay.object.ObjTruyen;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DsChuongActivity extends AppCompatActivity {
    ArrayList<ObjChuong> listCHuong = new ArrayList<>();
    AdapterChuong adapter;
    SqliteDAO database;

    ObjTruyen mTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_chuong);
        mTruyen = (ObjTruyen) getIntent().getSerializableExtra("truyen");

        setActionBar();
        database = new SqliteDAO(getApplicationContext());

        listCHuong = database.getChongTruyen(mTruyen.id);
        mTruyen.listChuong = listCHuong;
        adapter = new AdapterChuong(this, android.R.layout.simple_list_item_1, listCHuong);
        ListView lv = (ListView) findViewById(R.id.lvChuong);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mTruyen.mIndexChuong = i;
                Intent intent = new Intent(getApplicationContext(), DocTruyenActivity.class);
                intent.putExtra("truyen", mTruyen);

                startActivity(intent);
            }
        });
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(mTruyen.tenTruyen.toString());
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
