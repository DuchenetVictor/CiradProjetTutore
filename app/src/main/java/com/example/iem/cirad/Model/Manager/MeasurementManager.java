package com.example.iem.cirad.Model.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.iem.cirad.Controller.MySQLite;
import com.example.iem.cirad.Model.Pojo.Action;
import com.example.iem.cirad.Model.Pojo.Parcel;

import java.util.ArrayList;

/**
 * Created by David_tepoche on 06/01/2017.
 */

public class MeasurementManager {

    private static MeasurementManager sInstance;
    private SQLiteDatabase db;
    private MySQLite maBaseSQLite;

    private static final String TABLE_NAME_MEASUREMENT = "ParcelAction";
    private static final String KEY_ID_ACTION = "Id_Action";
    private static final String KEY_ID_PARCELL = "Id_Parcel";


    // Singleton
    public static synchronized MeasurementManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MeasurementManager(context);
        }
        return sInstance;
    }


    private MeasurementManager(Context context) {
        maBaseSQLite = MySQLite.getInstance(context);

        // open Table on READONLY
        db = maBaseSQLite.getReadableDatabase();
    }

    public void close() {
        db.close();
    }


    public void SetActionsInParcel (ArrayList<Action> actions, Parcel parcel){
        ContentValues values = new ContentValues();
        for (Action action : actions ) {
            values.put(KEY_ID_ACTION, action.getId());
            values.put(KEY_ID_PARCELL, parcel.getId());

            //on insère l'objet dans la BDD via le ContentValues
            db.insert(TABLE_NAME_MEASUREMENT, null, values);
        }


    }
}
