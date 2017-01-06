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
    private static final String KEY_EMERGENCYLEVEL_ACTION = "EmergencyLevel";
    private static final String KEY_ISTREATMENT_ACTION = "isTreatment";//todo gere le probleme conversion boolean
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
        Action action = new Action();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_ACTION, null);


        try {
            if (cursor.moveToNext()) {
                action.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_ACTION)));
                action.setEmergencyLevel(cursor.getInt(cursor.getColumnIndex(KEY_EMERGENCYLEVEL_ACTION)));
                action.setTreatmentLevel(cursor.getInt(cursor.getColumnIndex(KEY_TREATMENTLEVEL_ACTION)));
                action.setRemark(cursor.getString(cursor.getColumnIndex(KEY_REMARK_ACTION)));
                action.setDateMeasure(Date.valueOf(cursor.getString(cursor.getColumnIndex(KEY_DATEMEASURE_ACTION))));///todo getString pour une date ...

                actions.add(action);
            }
        }
        finally {
            cursor.close();
        }

        return actions;
    }

    public void SetAction(Action action){
            ContentValues values = new ContentValues();
            //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
            values.put(COL_ISBN, livre.getIsbn());
            values.put(COL_TITRE, livre.getTitre());
            //on insère l'objet dans la BDD via le ContentValues
            return bdd.insert(TABLE_LIVRES, null, values);
    }
}