package com.example.iem.cirad.Model.Manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.iem.cirad.Controller.MySQLite;
import com.example.iem.cirad.Model.Pojo.Picture;

/**
 * Created by iem on 12/01/2017.
 */

public class PictureManager {

    private static PictureManager sInstance;
    private SQLiteDatabase db;
    private MySQLite maBaseSQLite;

    private static final String TABLE_NAME_PICTURE = "Picture";

    public static final String KEY_ID_PICTURE = "Id";
    public static final String KEY_BLOB_PICTURE = "Blob";
    public static final String KEY_IDACTION_PICTURE = "Id_Action";



    // Singleton
    public static synchronized PictureManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PictureManager(context);
        }
        return sInstance;
    }


    private PictureManager(Context context) {
        maBaseSQLite = MySQLite.getInstance(context);

        // open Table on READONLY
        db = maBaseSQLite.getReadableDatabase();
    }

    public void close() {
        db.close();
    }

   //todo faire manager de picture

}
