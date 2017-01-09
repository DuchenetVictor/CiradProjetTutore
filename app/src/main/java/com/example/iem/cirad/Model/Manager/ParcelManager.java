package com.example.iem.cirad.Model.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.iem.cirad.Controller.MySQLite;
import com.example.iem.cirad.Model.Pojo.Parcel;

import java.util.ArrayList;

/**
 * Created by David_tepoche on 06/01/2017.
 */

public class ParcelManager {

    private static ParcelManager sInstance;
    private SQLiteDatabase db;
    private MySQLite maBaseSQLite;

    private static final String TABLE_NAME_PARCEL = "Parcel";
    private static final String KEY_ID_PARCEL = "Id";
    private static final String KEY_NAME_PARCEL = "Name";

    // Singleton
    public static synchronized ParcelManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ParcelManager(context);
        }
        return sInstance;
    }


    private ParcelManager(Context context) {
        maBaseSQLite = MySQLite.getInstance(context);

        // open Table on READONLY
        db = maBaseSQLite.getReadableDatabase();
    }

    public void close() {
        db.close();
    }

    public ArrayList<Parcel> getParcels() {

        ArrayList<Parcel> parcels = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_PARCEL, null);
        try {
            cursor.moveToFirst();
            do {
                Parcel parcel = new Parcel();
                parcel.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_PARCEL)));
                parcel.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_PARCEL)));
                parcels.add(parcel);

            } while (cursor.moveToNext());
        } catch (Exception e) {
            Log.d("bdd", e.getMessage());
        } finally {
            cursor.close();
        }
        return parcels;
    }

    public long setParcel(Parcel parcel){
        ContentValues values = new ContentValues();
        values.put(KEY_ID_PARCEL, parcel.getId());
        values.put(KEY_NAME_PARCEL, parcel.getName());
        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_NAME_PARCEL, null, values);
    }
}
