package com.example.gd.doctruyenhay.object;


import android.database.Cursor;

import java.io.Serializable;

public class ObjChuong implements Serializable {
    public int id;
    public String tenChuong;
    public String id_truyen;
    public String noi_dung;
    public String chu_thich;

    public ObjChuong(int id, String tenChuong, String id_truyen, String noi_dung, String chu_thich) {
        this.id = id;
        this.tenChuong = tenChuong;
        this.id_truyen = id_truyen;
        this.chu_thich = chu_thich;
    }

    public ObjChuong(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.id_truyen = cursor.getString(1);
        this.tenChuong = cursor.getString(2);
        this.noi_dung = cursor.getString(3);
        this.chu_thich = cursor.getString(4);
    }

    public ObjChuong() {
    }
}
