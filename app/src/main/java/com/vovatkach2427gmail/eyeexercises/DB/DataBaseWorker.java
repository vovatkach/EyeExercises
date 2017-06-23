package com.vovatkach2427gmail.eyeexercises.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vovatkach2427gmail.eyeexercises.Model.DateModel;
import com.vovatkach2427gmail.eyeexercises.Model.StatisticsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vovat on 02.06.2017.
 */

public class DataBaseWorker {
    private MyDataBaseHelper dataBaseHelper;
    public DataBaseWorker(Context context)
    {
        dataBaseHelper=new MyDataBaseHelper(context);
    }
    public void close(){dataBaseHelper.close();}
    //--------------------------------------------
    public List<StatisticsModel> loadStatisticsAll()
    {
        List<StatisticsModel> list=new ArrayList<>();
        SQLiteDatabase database=dataBaseHelper.getReadableDatabase();
        Cursor cursor=database.query(Contact.STATISTICS.TABLE_NAME,new String[]{Contact.STATISTICS.ID,Contact.STATISTICS.DATE},null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            int idColIndex=cursor.getColumnIndex(Contact.STATISTICS.ID);
            int dateColIndex=cursor.getColumnIndex(Contact.STATISTICS.DATE);
            do
            {
                list.add(new StatisticsModel(cursor.getInt(idColIndex),DataBaseWorker.fromJsonToDateMotel(cursor.getString(dateColIndex))));
            }while (cursor.moveToNext());
        }
        database.close();
        return list;
    }

    public void addDate(DateModel dateModel)
    {
        SQLiteDatabase database=dataBaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Contact.STATISTICS.DATE,DataBaseWorker.fromDateToJson(dateModel));
        database.insert(Contact.STATISTICS.TABLE_NAME,null,contentValues);
        database.close();
    }
    public void clearStatistics()
    {
        SQLiteDatabase database=dataBaseHelper.getWritableDatabase();
        database.delete(Contact.STATISTICS.TABLE_NAME,null,null);
        database.close();
    }
    //---------------------------------------------------
    public static String fromDateToJson(DateModel dateModel)
    {
        GsonBuilder builder=new GsonBuilder();
        Gson gson=builder.create();
        return gson.toJson(dateModel);
    }

    public static DateModel fromJsonToDateMotel(String json)
    {
        GsonBuilder builder=new GsonBuilder();
        Gson gson=builder.create();
        return gson.fromJson(json,DateModel.class);
    }

}
