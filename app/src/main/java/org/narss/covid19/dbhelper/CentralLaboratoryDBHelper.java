package org.narss.covid19.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.narss.covid19.model.Hospital;
import org.narss.covid19.model.Laboratory;
import org.narss.covid19.model.PatientVisitedPlace;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CentralLaboratoryDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "central_laboratories.sqlite";
    public static String DB_PATH = "/data/data/org.narss.covid19/databases/";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public List<Laboratory> laboratoriesList;

    public CentralLaboratoryDBHelper(Context context) {
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
    public List<Laboratory> getLaboratoriesList() {
        SQLiteDatabase db = this.getReadableDatabase();
        laboratoriesList = new ArrayList<Laboratory>();
        Cursor res =  db.rawQuery( "select * from laboratories", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            Laboratory laboratory = new Laboratory();

            laboratory.setLabId(res.getInt(0));
            laboratory.setLabNameEn(res.getString(1));
            laboratory.setLabNameAr(res.getString(2));
            laboratory.setLabGovernorate(res.getString(3));
            laboratory.setLabLat(res.getDouble(4));
            laboratory.setLabLon(res.getDouble(5));
            laboratory.setLabStartTime(res.getInt(6));
            laboratory.setLabEndTime(res.getInt(7));
            laboratory.setLabOffDays(res.getString(8));
            laboratory.setLabDailyTestsNumber(res.getInt(9));
            laboratory.setLabTotalPerformedTests(res.getInt(10));
            laboratory.setLabNegativeTestsNumber(res.getInt(11));
            laboratory.setLabPositiveTestsNumber(res.getInt(12));
            laboratoriesList.add(laboratory);
            res.moveToNext();
        }
        return laboratoriesList;
    }
}
