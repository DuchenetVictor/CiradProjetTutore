package com.example.iem.cirad.Model.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.iem.cirad.Controller.MySQLite;
import com.example.iem.cirad.Model.Pojo.User;

import static com.example.iem.cirad.Helpers.BooleanHelper.*;

/**
 * Created by iem on 08/11/2016.
 */

public class UserManager {

    private static UserManager sInstance;
    private SQLiteDatabase db;
    private MySQLite maBaseSQLite;

    private static final String TABLE_NAME_USER = "User";


    private static final String KEY_ID_USER = "Id";
    private static final String KEY_LOGIN_USER = "Login";
    private static final String KEY_PASSWORD_USER = "Password";
    private static final String KEY_ISCHIEF_USER = "isChief";
    private static final String KEY_ISCONNECTED_USER = "isConnected";


    // Singleton
    public static synchronized UserManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserManager(context);
        }
        return sInstance;
    }

    private UserManager(Context context) {
        maBaseSQLite = MySQLite.getInstance(context);

        // open Table on READONLY
        db = maBaseSQLite.getReadableDatabase();
    }

    public void close() {
        db.close();
    }

    public User getUserById(int id) {
        Cursor cursor = db.rawQuery("SELECT * " +
                "   FROM " + TABLE_NAME_USER+
                "   WHERE "+ KEY_ID_USER+ " = ?",new String[]{String.valueOf(id)});
        User user = new User();

        try {
            cursor.moveToFirst();
            do{
                user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_USER)));
                user.setLogin(cursor.getString(cursor.getColumnIndex(KEY_LOGIN_USER)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD_USER)));
                user.setIsChief(intToBoolean(cursor.getInt(cursor.getColumnIndex(KEY_ISCHIEF_USER))));
                user.setIsConnected(intToBoolean(cursor.getInt(cursor.getColumnIndex(KEY_ISCONNECTED_USER))));
            } while(cursor.moveToNext());
        }
        catch (Exception e) {
            Log.d("bdd", e.getMessage());
        }
        finally {
            cursor.close();
        }
        return user;
    }

    public void SetISConnectedForUser(User user){

        ContentValues contentvalues = new ContentValues();
        contentvalues.put(KEY_ISCONNECTED_USER,booleanToInt(Boolean.TRUE));


        String strFilter = KEY_ID_USER+" = "+user.getId();
        try{
             db.update(TABLE_NAME_USER,contentvalues,strFilter,null);

        }catch (Exception e) {

        }
    }

    public long setUser(User user){
        ContentValues values = new ContentValues();
        values.put(KEY_ID_USER, user.getId());
        values.put(KEY_LOGIN_USER, user.getLogin());
        values.put(KEY_PASSWORD_USER, user.getPassword());
        values.put(KEY_ISCHIEF_USER, booleanToInt(user.getIsChief()));
        values.put(KEY_ISCONNECTED_USER, booleanToInt(user.getIsConnected()));

        //on insère l'objet dans la BDD via le ContentValues
        return db.replace(TABLE_NAME_USER, null, values);
    }
}
