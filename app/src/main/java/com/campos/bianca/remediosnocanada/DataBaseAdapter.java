package com.campos.bianca.remediosnocanada;

import java.io.IOException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseAdapter {
    protected static final String TAG = "DataAdapter";
    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;
    private Medicine medicineData;

    public DataBaseAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataBaseAdapter createDatabase() throws SQLException {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataBaseAdapter open() throws SQLException {
        try
        {
            mDbHelper.openDataBase();
//            mDbHelper.close();
            mDb = mDbHelper.getWritableDatabase();


        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public void updateFavorites(int id) {
//        String query = "UPDATE medicine SET favorites = 1 WHERE _id = ?";
//        int medicineId = id;
//        this.open();
//        mDb.execSQL(query, new String[]{String.valueOf(id)});
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.campos.bianca.remediosnocanada/databases/medicine.db", null, 0);
            ContentValues newValues = new ContentValues();
            newValues.put("favorites", 1);

            db.update("medicine", newValues, "_id=" + String.valueOf(id), null);
        } catch (Exception e){

        }


//        this.close();

    }

    //TODO: dislikeUpdate method

    public ArrayList<Medicine> getMainPage() {
            String query = "SELECT _id, name_pt, brand_name_pt FROM medicine ORDER BY name_pt ASC";
            ArrayList<Medicine> medicineList = new ArrayList<>();
            try {
                this.open();
                Cursor mCur = mDb.rawQuery(query, null);
                if (mCur!=null){
                    while (mCur.moveToNext()){
                        Medicine std = new Medicine(
                                mCur.getInt(0),
                                mCur.getString(1),
                                mCur.getString(2));
                        medicineList.add(std);
                    }
                }

            } catch (SQLException mSQLException){
                Log.e(TAG, "getTestData >>"+ mSQLException.toString());
                throw mSQLException;
            }finally {
                this.close();
            }
            return medicineList;
    }

    public Medicine getDetailPage(int id) {
        String query = "SELECT _id, name_pt, name, brand_name_pt, brand_name, clinical_use_pt, clinical_use, prescription_pt, prescription, favorites FROM medicine WHERE _id = ?";
        ArrayList<Medicine> medicineList = new ArrayList<>();
        try {
            this.open();
            Cursor mCur = mDb.rawQuery(query, new String[]{String.valueOf(id)});
            if (mCur!=null){
                while (mCur.moveToNext()){
                    medicineData = new Medicine(
                            mCur.getInt(0),
                            mCur.getString(1),
                            mCur.getString(2),
                            mCur.getString(3),
                            mCur.getString(4),
                            mCur.getString(5),
                            mCur.getString(6),
                            mCur.getString(7),
                            mCur.getString(8),
                            mCur.getInt(9));
                }
            }

        } catch (SQLException mSQLException){
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }finally {
            this.close();
        }
        return medicineData;
    }

    public ArrayList<Medicine> getFavoritesPage() {
        String query = "SELECT _id, name_pt, brand_name_pt FROM medicine WHERE favorites = 1 ORDER BY name_pt ASC";
        ArrayList<Medicine> medicineList = new ArrayList<>();
        try {
            this.open();
            Cursor mCur = mDb.rawQuery(query, null);
            if (mCur!=null){
                while (mCur.moveToNext()){
                    Medicine std = new Medicine(
                            mCur.getInt(0),
                            mCur.getString(1),
                            mCur.getString(2));
                    medicineList.add(std);
                }
            }

        } catch (SQLException mSQLException){
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }finally {
            this.close();
        }
        return medicineList;
    }

}
