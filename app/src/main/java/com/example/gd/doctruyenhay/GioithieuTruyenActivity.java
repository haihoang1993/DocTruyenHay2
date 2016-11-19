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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gd.doctruyenhay.object.ObjTruyen;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GioithieuTruyenActivity extends AppCompatActivity implements View.OnClickListener {

    ObjTruyen mTruyen;
    ArrayList<ObjTruyen> listTruyen = new ArrayList<>();
    private TextView tvTenTruyen;
    private TextView tvTenTacGia;
    private ImageView imgAvataTruyen;
    private Button btnBatDauDoc;
    private TextView tvNoidung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioithieu_truyen);

        mTruyen = (ObjTruyen) getIntent().getSerializableExtra("truyen");
        setActionBar();
        initView();
        setImgae();
    }

    private void setImgae() {
        AssetManager assetManager = getAssets();
        InputStream is = null;
        try {
            is = assetManager.open("img/" + mTruyen.id + ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        imgAvataTruyen.setImageBitmap(bitmap);
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
    }

    private void initView() {
        tvTenTruyen = (TextView) findViewById(R.id.tvTenTruyen);
        tvTenTacGia = (TextView) findViewById(R.id.tvTenTacGia);
        imgAvataTruyen = (ImageView) findViewById(R.id.imgAvataTruyen);
        btnBatDauDoc = (Button) findViewById(R.id.btnBatDauDoc);
        tvNoidung = (TextView) findViewById(R.id.tvNoidung);

        tvTenTruyen.setText(mTruyen.tenTruyen.toString());
        tvTenTacGia.setText(mTruyen.tacGia.toString());
        tvNoidung.setText(mTruyen.moTa);

        btnBatDauDoc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBatDauDoc:
                Intent intent = new Intent(getApplicationContext(), DsChuongActivity.class);
                intent.putExtra("truyen", mTruyen);
                startActivity(intent);
                break;
        }
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
