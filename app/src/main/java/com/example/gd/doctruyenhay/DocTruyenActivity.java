package com.example.gd.doctruyenhay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DocTruyenActivity extends AppCompatActivity {
    ImageView btZoomOut,btZoomIn;
    TextView tvNoidung;
    float size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        btZoomIn=(ImageView) findViewById(R.id.btnZoomIn);
        btZoomOut=(ImageView) findViewById(R.id.btnZoomOut);
        tvNoidung=(TextView) findViewById(R.id.tvDoctruyen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        size=tvNoidung.getTextSize();
        btZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size+=2f;
                tvNoidung.setTextSize(size);
            }
        });
        btZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size-=2f;
                tvNoidung.setTextSize(size);
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
