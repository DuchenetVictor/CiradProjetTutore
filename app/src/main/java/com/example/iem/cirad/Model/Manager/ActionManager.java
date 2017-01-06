package com.example.iem.cirad.Model.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.iem.cirad.Controller.MySQLite;
import com.example.iem.cirad.Model.Pojo.Action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iem on 08/11/2016.
 */

public class ActionManager {

    private static ActionManager sInstance;
    private SQLiteDatabase db;
    private MySQLite maBaseSQLite;

    private static final String TABLE_NAME_ACTION = "Action";
    private static final String KEY_ID_ACTION = "Id";
    private static final String KEY_NAME_ACTION = "Name";
    private static final String KEY_EMERGENCYLEVEL_ACTION = "EmergencyLevel";
    private static final String KEY_ISTREATMENT_ACTION = "isTreatment";
    private static final String KEY_TREATMENTLEVEL_ACTION = "TreatmentLevel";
    private static final String KEY_REMARK_ACTION = "Remark";
    private static final String KEY_DATEMEASURE_ACTION = "DateMeasure";


    // Singleton
    public static synchronized ActionManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ActionManager(context);
        }
        return sInstance;
    }


    private ActionManager(Context context) {
        maBaseSQLite = MySQLite.getInstance(context);

        // open Table on READONLY
        db = maBaseSQLite.getReadableDatabase();
    }

    public void close() {
        db.close();
    }

    public ArrayList<Action> getActions() {

        ArrayList<Action> actions = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_ACTION, null);
        try {
            /*if (cursor.moveToNext())
            do{
                Action action = new Action();

                action.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_ACTION)));
                action.setEmergencyLevel(cursor.getInt(cursor.getColumnIndex(KEY_EMERGENCYLEVEL_ACTION)));
                action.setIsTreatment(cursor.getInt(cursor.getColumnIndex(KEY_TREATMENTLEVEL_ACTION)));
                action.setTreatmentLevel(cursor.getInt(cursor.getColumnIndex(KEY_TREATMENTLEVEL_ACTION)));
                action.setRemark(cursor.getString(cursor.getColumnIndex(KEY_REMARK_ACTION)));
                action.setDateMeasure(Date.valueOf(cursor.getString(cursor.getColumnIndex(KEY_DATEMEASURE_ACTION))));

                actions.add(action);
            } while(cursor.moveToNext());
            */
            cursor.moveToFirst();
            while (!cursor.isLast()){
                Action action = new Action();

                action.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_ACTION)));
                action.setEmergencyLevel(cursor.getInt(cursor.getColumnIndex(KEY_EMERGENCYLEVEL_ACTION)));
                action.setIsTreatment(cursor.getInt(cursor.getColumnIndex(KEY_TREATMENTLEVEL_ACTION)));
                action.setTreatmentLevel(cursor.getInt(cursor.getColumnIndex(KEY_TREATMENTLEVEL_ACTION)));
                action.setRemark(cursor.getString(cursor.getColumnIndex(KEY_REMARK_ACTION)));
                action.setDateMeasure(Date.valueOf(cursor.getString(cursor.getColumnIndex(KEY_DATEMEASURE_ACTION))));

                actions.add(action);
            }

        }
        finally {
            cursor.close();
        }
        return actions;
    }

    public long SetAction(Action action){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_ACTION, action.getName());
        values.put(KEY_EMERGENCYLEVEL_ACTION, action.getEmergencyLevel());
        values.put(KEY_ISTREATMENT_ACTION, action.getIsTreatment());
        values.put(KEY_TREATMENTLEVEL_ACTION,action.getTreatmentLevel());
        values.put(KEY_REMARK_ACTION,action.getRemark());
        values.put(KEY_DATEMEASURE_ACTION,action.getDateMeasure().toString());

        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_NAME_ACTION, null, values);
    }
}