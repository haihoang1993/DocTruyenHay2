package com.example.gd.doctruyenhay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gd.doctruyenhay.database.SqliteDAO;
import com.example.gd.doctruyenhay.object.ObjTruyen;

public class DocTruyenActivity extends AppCompatActivity {

    float size;
    ObjTruyen mTruyen;
    private TextView tvNoiDung;
    private ImageView btnZoomOut, btnZoomIn, imgBtnBack, imgBtnNext, btnLike;

    SqliteDAO sqliteDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        sqliteDAO = new SqliteDAO(getApplicationContext());
        mTruyen = (ObjTruyen) getIntent().getSerializableExtra("truyen");
        initView();
        Like();
    }

    private void initView() {
        btnLike = (ImageView) findViewById(R.id.btnLike);
        tvNoiDung = (TextView) findViewById(R.id.tvNoiDung);
        btnZoomOut = (ImageView) findViewById(R.id.btnZoomOut);
        btnZoomIn = (ImageView) findViewById(R.id.btnZoomIn);
        imgBtnBack = (ImageView) findViewById(R.id.imgBtnBack);
        imgBtnNext = (ImageView) findViewById(R.id.imgBtnNext);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chương " + (mTruyen.mIndexChuong + 1) + ":" + mTruyen.listChuong.get(mTruyen.mIndexChuong).tenChuong);
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
        getSupportActionBar().setTitle("Chương " + (mTruyen.mIndexChuong + 1) + ":" + mTruyen.listChuong.get(mTruyen.mIndexChuong).tenChuong);
        tvNoiDung.setText(mTruyen.listChuong.get(mTruyen.mIndexChuong).noi_dung);
    }

    private void Like() {
        if (mTruyen.yeuThich) {
            btnLike.setImageResource(R.mipmap.ic_g);
            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sqliteDAO.updateLikeTruyen(mTruyen.id, -1) != -1) {
                        mTruyen.yeuThich = false;
                        Like();
                    }
                    ;
                }
            });
            return;
        }
        btnLike.setImageResource(R.mipmap.ic_lag);
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sqliteDAO.updateLikeTruyen(mTruyen.id, 1) != -1) {
                    mTruyen.yeuThich = true;
                    Like();
                }
                ;
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
