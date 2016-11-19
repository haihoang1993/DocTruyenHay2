package com.example.gd.doctruyenhay.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gd.doctruyenhay.R;
import com.example.gd.doctruyenhay.object.ObjTruyen;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class AdapterTruyen extends ArrayAdapter<ObjTruyen> {
    Context mContext;
    ArrayList<ObjTruyen> mListTruyen;

    public AdapterTruyen(Context context, int resource, ArrayList<ObjTruyen> list) {
        super(context, resource, list);
        mContext = context;
        mListTruyen = list;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.item_truyen, null, true);
        TextView tv = (TextView) rowView.findViewById(R.id.tvItemTruyen);
        ImageView img = (ImageView) rowView.findViewById(R.id.imgItemTruyen);
        ObjTruyen truyen = mListTruyen.get(position);

        tv.setText(truyen.tenTruyen);
        setImgae(truyen, img);
        return rowView;
    }

    private void setImgae(ObjTruyen truyen, ImageView imgView) {
        AssetManager assetManager = mContext.getAssets();
        InputStream is = null;
        try {
            is = assetManager.open("img/" + truyen.id + ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        imgView.setImageBitmap(bitmap);
    }
}
