package com.example.iem.cirad.Model.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iem.cirad.Controller.MySQLite;

import java.util.ArrayList;

/**
 * Created by David_tepoche on 10/01/2017.
 */

public class TypeActionManager {
    private static TypeActionManager sInstance;
    private SQLiteDatabase db;
    private MySQLite maBaseSQLite;

    private static final String TABLE_NAME_ACTION = "TypeAction";

    public static final String KEY_NAME_TYPEACTION = "Name";


    // Singleton
    public static synchronized TypeActionManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new TypeActionManager (context);
        }
        return sInstance;
    }


    private TypeActionManager (Context context) {
        maBaseSQLite = MySQLite.getInstance(context);

        // open Table on READONLY
        db = maBaseSQLite.getReadableDatabase();
    }

    public void close() {
        db.close();
    }

    public void setTypesAction(ArrayList<String> typesAction){

        db.delete(TABLE_NAME_ACTION,null,null);
        ContentValues contentvalues = new ContentValues();
        for (String typeaction: typesAction) {
            contentvalues.put(KEY_NAME_TYPEACTION,typeaction);
            db.replace(TABLE_NAME_ACTION,null,contentvalues);
        }
    }
    public ArrayList<String> getTypesAction(){
        ArrayList<String> typesaction = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT *"+
                "   FROM    "+TABLE_NAME_ACTION,null);
        try{
            cursor.moveToFirst();
            do {
                typesaction.add(cursor.getString(cursor.getColumnIndex(KEY_NAME_TYPEACTION)));
            }while (cursor.moveToNext());
        }catch (Exception e){
            e.getMessage();
        }finally {
            cursor.close();
        }
        return typesaction;
    }

}
