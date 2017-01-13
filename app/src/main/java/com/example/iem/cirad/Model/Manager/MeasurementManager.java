package com.example.iem.cirad.Model.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.iem.cirad.Controller.MySQLite;
import com.example.iem.cirad.Model.Pojo.Action;
import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.Model.Pojo.User;

import java.sql.Date;
import java.util.ArrayList;

import static com.example.iem.cirad.Helpers.BooleanHelper.booleanToInt;
import static com.example.iem.cirad.Helpers.BooleanHelper.intToBoolean;

import static com.example.iem.cirad.Model.Manager.ActionManager.*;
import static com.example.iem.cirad.Model.Manager.ParcelManager.*;


/**
 * Created by David_tepoche on 06/01/2017.
 */

public class MeasurementManager {

    private static final String TABLE_NAME_MEASUREMENT = "Measurement";
    private static final String TABLE_NAME_PARCEL = "Parcel";
    private static final String TABLE_NAME_ACTION = "Action";
    private static final String KEY_IDACTION_MEASUREMENT = "Id_Action";
    private static final String KEY_IDPARCEL_MEASUREMENT = "Id_Parcel";
    private static final String KEY_ISSYNCHRO_MEASUREMENT = "isSynchro";
    private static MeasurementManager sInstance;
    private SQLiteDatabase db;
    private MySQLite maBaseSQLite;


    private MeasurementManager(Context context) {
        maBaseSQLite = MySQLite.getInstance(context);

        // open Table on READONLY
        db = maBaseSQLite.getReadableDatabase();
    }

    // Singleton
    public static synchronized MeasurementManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MeasurementManager(context);
        }
        return sInstance;
    }

    public void close() {
        db.close();
    }


    public void setMeasure(ArrayList<Action> actions, Parcel parcel) {
        ContentValues values = new ContentValues();
        for (Action action : actions) {
            values.put(KEY_IDACTION_MEASUREMENT, action.getId());
            values.put(KEY_IDPARCEL_MEASUREMENT, parcel.getId());
            values.put(KEY_ISSYNCHRO_MEASUREMENT, Boolean.FALSE);

            //on insère l'objet dans la BDD via le ContentValues
            db.replace(TABLE_NAME_MEASUREMENT, null, values);
        }
    }

    public Date getLastDateInMeasurement(Parcel parcel, boolean synch) {
        ArrayList<Action> actions = new ArrayList<>();
        Date dateYoungest = new Date(0);

        Cursor cursor = db.rawQuery("   SELECT " + TABLE_NAME_ACTION + "." + KEY_DATEMEASURE_ACTION +
                "   FROM " + TABLE_NAME_MEASUREMENT + ", " + TABLE_NAME_ACTION +
                "   WHERE " + TABLE_NAME_ACTION + "." + KEY_ID_ACTION + " = " + TABLE_NAME_MEASUREMENT + "." + KEY_IDACTION_MEASUREMENT +
                "   AND " + KEY_IDPARCEL_MEASUREMENT + " = ?" +
                "   AND " + KEY_ISSYNCHRO_MEASUREMENT + " = ?", new String[]{String.valueOf(parcel.getId()), String.valueOf(booleanToInt(synch))});

        try {
            cursor.moveToFirst();
            do {
                Date dateAction = Date.valueOf(cursor.getString(cursor.getColumnIndex(KEY_DATEMEASURE_ACTION)));
                if (dateYoungest.after(dateAction)) {
                    dateYoungest = dateAction;
                }
            } while (cursor.moveToNext());
        } catch (Exception e) {
            Log.d("bdd", e.getMessage());
        } finally {
            cursor.close();
        }
        return dateYoungest;
    }



    public ArrayList<Action> getActionsInParcel(Parcel parcel, boolean synch) {

        ArrayList<Action> actions = new ArrayList<>();

        //get all actions in Table Measurement, with id of parcel and synch in param
        Cursor cursor = db.rawQuery("   SELECT " + TABLE_NAME_ACTION + ".*" +
                "   FROM " + TABLE_NAME_MEASUREMENT + ", " + TABLE_NAME_ACTION +
                "   WHERE " + TABLE_NAME_ACTION + "." + KEY_ID_ACTION + " = " + TABLE_NAME_MEASUREMENT + "." + KEY_IDACTION_MEASUREMENT +
                "   AND " + KEY_IDPARCEL_MEASUREMENT + " = ?" +
                "   AND " + KEY_ISSYNCHRO_MEASUREMENT + " = ?", new String[]{String.valueOf(parcel.getId()), String.valueOf(booleanToInt(synch))});


        try {
            cursor.moveToFirst();
            do {
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

            } while (cursor.moveToNext());
        } catch (Exception e) {
            Log.d("bdd", e.getMessage());
        } finally {
            cursor.close();
        }
        return actions;
    }

    public ArrayList<Action> getActionsInParcelByUser(Parcel parcel, boolean synch,User user) {

        ArrayList<Action> actions = new ArrayList<>();

        //get all actions in Table Measurement, with id of parcel and synch in param
        Cursor cursor = db.rawQuery("   SELECT " + TABLE_NAME_ACTION + ".*" +
                "   FROM " + TABLE_NAME_MEASUREMENT + ", " + TABLE_NAME_ACTION +
                "   WHERE " + TABLE_NAME_ACTION + "." + KEY_ID_ACTION + " = " + TABLE_NAME_MEASUREMENT + "." + KEY_IDACTION_MEASUREMENT +
                "   AND " + KEY_IDPARCEL_MEASUREMENT + " = ?" +
                "   AND " + KEY_ISSYNCHRO_MEASUREMENT + " = ?"+
                "   AND " + KEY_IDUSER_ACTION+ " = ?", new String[]{String.valueOf(parcel.getId()), String.valueOf(booleanToInt(synch)),String.valueOf(user.getId())});


        try {
            cursor.moveToFirst();
            do {
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

            } while (cursor.moveToNext());
        } catch (Exception e) {
            Log.d("bdd", e.getMessage());
        } finally {
            cursor.close();
        }
        return actions;
    }

    public ArrayList<Parcel> getParcelsBySynchro(boolean bool) {

        ArrayList<Parcel> parcels = new ArrayList<>();

        Cursor cursor = db.rawQuery("   SELECT * " +
                "   FROM " + TABLE_NAME_PARCEL + ", " + TABLE_NAME_MEASUREMENT +
                "   WHERE " + TABLE_NAME_MEASUREMENT + "." + KEY_IDPARCEL_MEASUREMENT + " = " + TABLE_NAME_PARCEL + "." + KEY_ID_PARCEL +
                "   AND " + KEY_ISSYNCHRO_MEASUREMENT + " = ?" +
                "   GROUP BY " + KEY_IDPARCEL_MEASUREMENT, new String[]{String.valueOf(booleanToInt(bool))});

        try {
            cursor.moveToFirst();
            do {
                Parcel parcel = new Parcel();
                parcel.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_PARCEL)));
                parcel.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_PARCEL)));
                parcel.setLongitude(cursor.getString(cursor.getColumnIndex(KEY_LONGITUDE_PARCEL)));
                parcel.setLatitude(cursor.getString(cursor.getColumnIndex(KEY_LATITUDE_PARCEL)));
                parcels.add(parcel);

            } while (cursor.moveToNext());
        } catch (Exception e) {
            Log.d("bdd", e.getMessage());
        } finally {
            cursor.close();
        }
        return parcels;
    }


    public ArrayList<Parcel> getParcelsBySynchroAndUser(boolean bool,User user) {

        ArrayList<Parcel> parcels = new ArrayList<>();

        Cursor cursor = db.rawQuery("   SELECT * " +
                "   FROM " + TABLE_NAME_PARCEL + ", " + TABLE_NAME_MEASUREMENT +", "+ TABLE_NAME_ACTION+
                "   WHERE " + TABLE_NAME_MEASUREMENT + "." + KEY_IDPARCEL_MEASUREMENT + " = " + TABLE_NAME_PARCEL + "." + KEY_ID_PARCEL +
                "   AND "+ TABLE_NAME_MEASUREMENT+"."+KEY_IDACTION_MEASUREMENT+" = "+ TABLE_NAME_ACTION+"."+KEY_ID_ACTION+
                "   AND " + KEY_ISSYNCHRO_MEASUREMENT + " = ?" +
                "   AND "+ KEY_IDUSER_ACTION + " = ?"+
                "   GROUP BY " + KEY_IDPARCEL_MEASUREMENT, new String[]{String.valueOf(booleanToInt(bool)),String.valueOf(user.getId())});

        try {
            cursor.moveToFirst();
            do {
                Parcel parcel = new Parcel();
                parcel.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_PARCEL)));
                parcel.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_PARCEL)));
                parcel.setLongitude(cursor.getString(cursor.getColumnIndex(KEY_LONGITUDE_PARCEL)));
                parcel.setLatitude(cursor.getString(cursor.getColumnIndex(KEY_LATITUDE_PARCEL)));
                parcels.add(parcel);

            } while (cursor.moveToNext());
        } catch (Exception e) {
            Log.d("bdd", e.getMessage());
        } finally {
            cursor.close();
        }
        return parcels;
    }

    public void updateMeasurementSynchro(Parcel parcel) {
        Cursor cursor = db.rawQuery("   SELECT * " +
                "   FROM " + TABLE_NAME_MEASUREMENT +
                "   WHERE " + TABLE_NAME_MEASUREMENT + "." + KEY_IDPARCEL_MEASUREMENT + " = ?" +
                "   AND " + KEY_ISSYNCHRO_MEASUREMENT + " = ?", new String[]{String.valueOf(parcel.getId()), String.valueOf(booleanToInt(Boolean.FALSE))});

        ArrayList<Integer> idAction = new ArrayList<>();

        try {
            cursor.moveToFirst();
            do {
                idAction.add(cursor.getInt(cursor.getColumnIndex(KEY_IDACTION_MEASUREMENT)));
            } while (cursor.moveToNext());

            ContentValues contentvalues = new ContentValues();
            contentvalues.put(KEY_ISSYNCHRO_MEASUREMENT,booleanToInt(Boolean.TRUE));
            for (int id : idAction) {

                String strFilter = KEY_IDPARCEL_MEASUREMENT+" = "+parcel.getId()+
                        "   AND "+ KEY_IDACTION_MEASUREMENT+ " = " +String.valueOf(id);

                db.update(TABLE_NAME_MEASUREMENT,contentvalues,strFilter,null);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            cursor.close();
        }
    }
}



