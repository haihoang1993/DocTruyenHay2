package com.example.gd.doctruyenhay.object;

import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by funkoigame on 21/10/2016.
 */

public class ObjTruyen implements Serializable {
    public String id;
    public String tenTruyen;
    public String moTa;
    public String tacGia;
    public String idTheloai;
    public boolean yeuThich;

    public int mIndexChuong;
    public ArrayList<ObjChuong> listChuong = new ArrayList<>();

    public ObjTruyen(String id, String tenTruyen, String moTa, String tacGia, String idTheloai) {
        this.id = id;
        this.tenTruyen = tenTruyen;
        this.moTa = moTa;
        this.tacGia = tacGia;
        this.idTheloai = idTheloai;
    }

    public ObjTruyen(Cursor cursor) {
        this.id = cursor.getString(0);
        this.tenTruyen = cursor.getString(1);
        this.moTa = cursor.getString(2);
        this.tacGia = cursor.getString(3);
        this.idTheloai = cursor.getString(4);
        if (cursor.getInt(6) != -1) yeuThich = true;
        else yeuThich = false;
    }

    public ObjTruyen() {
    }
}
