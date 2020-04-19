package org.narss.covid19.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import org.narss.covid19.model.Hospital;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amira on 10/11/2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Hospitals_List-point.sqlite";
    public static String DB_PATH = "/data/data/org.narss.covid19/databases/";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public List<Hospital> hospitalList;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch(SQLiteException e) {
            //throw new Error("Database does't exist");
        }
        if(checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DB_PATH + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist){
            //copyDataBase();
        } else {
            this.getReadableDatabase();
            //this.close();
            try{
                copyDataBase();
                Log.e("DataBaseHelper", "createDatabase database created");
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }
    public List<Hospital> getHospitalList() {
        SQLiteDatabase db = this.getReadableDatabase();
        hospitalList = new ArrayList<Hospital>();
        Cursor res =  db.rawQuery( "select * from hospitals_list_point", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            Hospital hospital = new Hospital();
            hospital.setId(res.getString(2));
            hospital.setName(res.getString(3));
            hospital.setGovernorate(res.getString(4));
            hospital.setLat(res.getDouble(5));
            hospital.setLon(res.getDouble(6));
            hospitalList.add(hospital);
            res.moveToNext();
        }
        return hospitalList;
    }
}
