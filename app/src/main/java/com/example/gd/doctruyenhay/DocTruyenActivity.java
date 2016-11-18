package com.example.gd.doctruyenhay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gd.doctruyenhay.object.ObjTruyen;

public class DocTruyenActivity extends AppCompatActivity {

    float size;
    ObjTruyen mTruyen;
    private TextView tvNoiDung;
    private ImageView btnZoomOut, btnZoomIn, imgBtnBack, imgBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        mTruyen = (ObjTruyen) getIntent().getSerializableExtra("truyen");
        initView();
    }

    private void initView() {
        tvNoiDung = (TextView) findViewById(R.id.tvNoiDung);
        btnZoomOut = (ImageView) findViewById(R.id.btnZoomOut);
        btnZoomIn = (ImageView) findViewById(R.id.btnZoomIn);
        imgBtnBack = (ImageView) findViewById(R.id.imgBtnBack);
        imgBtnNext = (ImageView) findViewById(R.id.imgBtnNext);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mTruyen.listChuong.get(mTruyen.mIndexChuong).tenChuong);
        tvNoiDung.setText(mTruyen.listChuong.get(mTruyen.mIndexChuong).noi_dung);

        size = tvNoiDung.getTextSize();
        btnZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size += 2f;
                tvNoiDung.setTextSize(size);
            }
        });
        btnZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size -= 2f;
                tvNoiDung.setTextSize(size);
            }
        });

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTruyen.mIndexChuong--;
                if(mTruyen.mIndexChuong<0) mTruyen.mIndexChuong=0;
                show();
            }
        });
        imgBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTruyen.mIndexChuong++;
                if(mTruyen.mIndexChuong>=mTruyen.listChuong.size()) mTruyen.mIndexChuong=mTruyen.listChuong.size()-1;
                show();
            }
        });
    }

    private void show(){
        tvNoiDung.setText(mTruyen.listChuong.get(mTruyen.mIndexChuong ).noi_dung);
        getSupportActionBar().setTitle(mTruyen.listChuong.get(mTruyen.mIndexChuong).tenChuong);
        tvNoiDung.setText(mTruyen.listChuong.get(mTruyen.mIndexChuong).noi_dung);
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
