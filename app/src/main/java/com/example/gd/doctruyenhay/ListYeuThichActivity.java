package com.example.gd.doctruyenhay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gd.doctruyenhay.adapter.AdapterTruyen;
import com.example.gd.doctruyenhay.database.SqliteDAO;
import com.example.gd.doctruyenhay.object.ObjTruyen;

import java.util.ArrayList;

public class ListYeuThichActivity extends AppCompatActivity {
    ListView lvTruyen;
    AdapterTruyen adapter;
    ArrayList<ObjTruyen> listTruyen = new ArrayList<>();

    SqliteDAO database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_yeu_thich);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Yêu Thích");
        database = new SqliteDAO(getApplicationContext());
        listTruyen = database.getYeuTHich();
        init();
    }

    private void init() {
        lvTruyen = (ListView) findViewById(R.id.lvTruyenYeuthich);
        adapter = new AdapterTruyen(getApplicationContext(), R.layout.item_truyen, listTruyen);
        Log.d("aaa", listTruyen.toString());
        lvTruyen.setAdapter(adapter);
        lvTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), GioithieuTruyenActivity.class);
                Log.d("truyen", listTruyen.get(i).toString());
                intent.putExtra("truyen", listTruyen.get(i));
                startActivity(intent);
            }
        });

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
