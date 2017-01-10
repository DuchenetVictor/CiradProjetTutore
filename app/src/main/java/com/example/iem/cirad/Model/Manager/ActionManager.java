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

import static com.example.iem.cirad.Helpers.BooleanHelper.booleanToInt;
import static com.example.iem.cirad.Helpers.BooleanHelper.intToBoolean;

/**
 * Created by iem on 08/11/2016.
 */

public class ActionManager {

    private static ActionManager sInstance;
    private SQLiteDatabase db;
    private MySQLite maBaseSQLite;

    private static final String TABLE_NAME_ACTION = "Action";

    public static final String KEY_ID_ACTION = "Id";
    public static final String KEY_NAME_ACTION = "Name";
    public static final String KEY_EMERGENCYLEVEL_ACTION = "EmergencyLevel";
    public  static final String KEY_ISTREATMENT_ACTION = "isTreatment";
    public  static final String KEY_TREATMENTLEVEL_ACTION = "TreatmentLevel";
    public  static final String KEY_REMARK_ACTION = "Remark";
    public  static final String KEY_DATEMEASURE_ACTION = "DateMeasure";
    public  static final String KEY_IDUSER_ACTION = "Id_User";

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


        Cursor cursor = db.rawQuery("   SELECT * " +
                "   FROM " +TABLE_NAME_ACTION, null);
        try {
            cursor.moveToFirst();
            do{
                Action action = new Action();

                action.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_ACTION)));
                action.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_ACTION)));
                action.setEmergencyLevel(cursor.getInt(cursor.getColumnIndex(KEY_EMERGENCYLEVEL_ACTION)));
                action.setIsTreatment(intToBoolean(cursor.getInt(cursor.getColumnIndex(KEY_ISTREATMENT_ACTION))));
                action.setTreatmentLevel(cursor.getInt(cursor.getColumnIndex(KEY_TREATMENTLEVEL_ACTION)));
                action.setRemark(cursor.getString(cursor.getColumnIndex(KEY_REMARK_ACTION)));
                action.setDateMeasure(Date.valueOf(cursor.getString(cursor.getColumnIndex(KEY_DATEMEASURE_ACTION))));
                action.setIdUser(cursor.getInt(cursor.getColumnIndex(KEY_IDUSER_ACTION)));

                actions.add(action);

            } while(cursor.moveToNext());
        }
        catch (Exception e) {
            Log.d("bdd", e.getMessage());
        }
        finally {
            cursor.close();
        }
        return actions;
    }

    public Action getActionById(int id){
        Cursor cursor = db.rawQuery("  SELECT * " +
                " FROM "+ TABLE_NAME_ACTION+
                "WHERE "+ KEY_ID_ACTION+ " = ?",new String[]{String.valueOf(id)});

        Action action = new Action();

        try {
            cursor.moveToFirst();
            do{

                action.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_ACTION)));
                action.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_ACTION)));
                action.setEmergencyLevel(cursor.getInt(cursor.getColumnIndex(KEY_EMERGENCYLEVEL_ACTION)));
                action.setIsTreatment(intToBoolean(cursor.getInt(cursor.getColumnIndex(KEY_ISTREATMENT_ACTION))));
                action.setTreatmentLevel(cursor.getInt(cursor.getColumnIndex(KEY_TREATMENTLEVEL_ACTION)));
                action.setRemark(cursor.getString(cursor.getColumnIndex(KEY_REMARK_ACTION)));
                action.setDateMeasure(Date.valueOf(cursor.getString(cursor.getColumnIndex(KEY_DATEMEASURE_ACTION))));
                action.setIdUser(cursor.getInt(cursor.getColumnIndex(KEY_IDUSER_ACTION)));

            } while(cursor.moveToNext());
        }
        catch (Exception e) {
            Log.d("bdd", e.getMessage());
        }
        finally {
            cursor.close();
        }
        return action;
    }

    public void deleteActions(ArrayList<Action> actions)
    {
        for (Action action: actions) {
            db.delete(TABLE_NAME_ACTION, KEY_ID_ACTION + "= ?" + action.getId(), null);
        }
    }

    public void deleteAction(Action action)
    {

        db.delete(TABLE_NAME_ACTION, KEY_ID_ACTION + "= ?" + action.getId(), null);

    }

    public long setAction(Action action){
        ContentValues values = new ContentValues();
        long idaction = -1;
        values.put(KEY_NAME_ACTION, action.getName());
        values.put(KEY_EMERGENCYLEVEL_ACTION, action.getEmergencyLevel());
        values.put(KEY_ISTREATMENT_ACTION, booleanToInt(action.getIsTreatment()));
        values.put(KEY_TREATMENTLEVEL_ACTION,action.getTreatmentLevel());
        values.put(KEY_REMARK_ACTION,action.getRemark());
        values.put(KEY_DATEMEASURE_ACTION,String.valueOf(action.getDateMeasure()));
        values.put(KEY_IDUSER_ACTION,action.getIdUser());

        //on insère l'objet dans la BDD via le ContentValues
        try{
           idaction =  db.replace(TABLE_NAME_ACTION, null, values);

        }catch (Exception e){

        }
        return idaction;
    }
}
