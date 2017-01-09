package com.example.iem.cirad.Helpers;

/**
 * Created by iem on 09/01/2017.
 */

public class BooleanHelper {

    public static int booleanToInt(Boolean bool){

        if (bool){
            return 1;
        }
        else {
            return 0;
        }

    }

    public static boolean intToBoolean (int number){
        if (number == 0){
            return Boolean.FALSE;
        }else {
            return Boolean.TRUE;
        }

    }
}
