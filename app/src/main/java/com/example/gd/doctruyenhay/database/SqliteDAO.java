package com.example.gd.doctruyenhay.database;

import android.content.Context;
import android.database.Cursor;

import com.example.gd.doctruyenhay.object.ObjTruyen;

import java.util.ArrayList;

public class SqliteDAO {
    Context context;
    SqliteDatabase sqliteDatabase;
    SqliteDatabaseCallBack callbackDatabse;
    public SqliteDAO(){

    }



    public SqliteDAO(Context c){
        context=c;
        sqliteDatabase=new SqliteDatabase(context,"");
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


}
