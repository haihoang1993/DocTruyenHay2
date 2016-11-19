package com.example.gd.doctruyenhay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gd.doctruyenhay.R;
import com.example.gd.doctruyenhay.object.ObjChuong;

import java.util.ArrayList;


public class AdapterChuong extends ArrayAdapter<ObjChuong> {
    Context mContext;
    ArrayList<ObjChuong> mListTruyen;

    public AdapterChuong(Context context, int resource, ArrayList<ObjChuong> list) {
        super(context, resource, list);
        mContext = context;
        mListTruyen = list;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.item_chuong, null, true);
        TextView tv = (TextView) rowView.findViewById(R.id.tvItemChuong);

        ObjChuong truyen = mListTruyen.get(position);


        tv.setText("Chuong " + (position + 1) + ":" + truyen.tenChuong);
//        tv.setText(truyen.tenChuong);

        return rowView;
    }
}
