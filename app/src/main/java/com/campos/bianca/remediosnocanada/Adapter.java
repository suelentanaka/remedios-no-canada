package com.campos.bianca.remediosnocanada;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Adapter {
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public Adapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public Adapter createDatabase() throws SQLException
    {
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

    public Adapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }


    public ArrayList<Medicine> getTestData()
    {

//            String sql ="SELECT * FROM medicine";
//
//            Cursor mCur = mDb.rawQuery(sql, null);
//            if (mCur!=null)
//            {
//                mCur.moveToNext();
//            }
//            return mCur;


            String query = "SELECT _id, name_pt, brand_name_pt FROM medicine";
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
