package com.vovatkach2427gmail.eyeexercises.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by vovat on 02.06.2017.
 */

public class MyDataBaseHelper extends SQLiteAssetHelper {
    public MyDataBaseHelper(Context context) {
        super(context, Contact.DATABASE_NAME, null, Contact.DATABASE_VERSION);
    }
}
