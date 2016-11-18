package com.example.gd.doctruyenhay.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gd.doctruyenhay.object.ObjChuong;
import com.example.gd.doctruyenhay.object.ObjTruyen;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;


public class SqliteDatabase extends SQLiteOpenHelper {
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.example.gd.doctruyenhay/databases/";

    private static String DB_NAME = "database.sqlite";

    private SQLiteDatabase myDataBase;

    private  Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public SqliteDatabase(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getWritableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException{

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        try {
////            CopyDataBaseFromAsset();
//            createDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub

    }
    public Cursor getDsTruyen(String idLoai){
      //  openDB();
        String query = "SELECT * FROM Truyen where id_the_loai=?";    Cursor cursor = myDataBase.rawQuery(query, new String[]{idLoai});
     ///   closeDB();
        return cursor;
    }

    public ArrayList<ObjChuong> getDanhSachChuong(String idTruyen){
        ArrayList<ObjChuong> list=new ArrayList<>();
        String query = "SELECT * FROM Chuong where id_truyen=?";
        try {
            openDataBase();
            Cursor cursor = myDataBase.rawQuery(query, new String[]{idTruyen});
            if(cursor.moveToFirst()){
                do{
                    ObjChuong chuong=new ObjChuong(cursor);
                    list.add(chuong);
                }while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int setLikeTruyen(String idTruyen, int like) {
        int res = -1;
        try {
            openDataBase();
            ContentValues ct = new ContentValues();
            ct.put("yeu_thich", like);
            res = myDataBase.update("Truyen", ct, "id_truyen", new String[]{idTruyen + ""});

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return res;
    }

}
