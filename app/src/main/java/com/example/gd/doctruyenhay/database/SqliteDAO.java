package com.example.gd.doctruyenhay.database;

import android.content.Context;
import android.database.Cursor;

import com.example.gd.doctruyenhay.object.ObjChuong;
import com.example.gd.doctruyenhay.object.ObjTruyen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqliteDAO {
    Context context;
    SqliteDatabase sqliteDatabase;
    SqliteDatabaseCallBack callbackDatabse;
    public SqliteDAO(){

    }

    public SqliteDAO(Context c){
        context=c;
        sqliteDatabase=new SqliteDatabase(context);
        try {
            sqliteDatabase.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sqliteDatabase.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCallback(SqliteDatabaseCallBack cabllback){
        this.callbackDatabse=cabllback;
    }

    public void getDanhSachTruyen(String idLoai){
        Cursor cursor= sqliteDatabase.getDsTruyen(idLoai);
        if(cursor.moveToFirst()){
            do{
                ObjTruyen truyen=new ObjTruyen(cursor);
                callbackDatabse.showTruyen(truyen);
            }while (cursor.moveToNext());
        }
    }

    public ArrayList<ObjChuong> getChongTruyen(String idTruyen){
        return sqliteDatabase.getDanhSachChuong(idTruyen);

    }
}
