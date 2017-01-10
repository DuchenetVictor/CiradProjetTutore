package com.example.iem.cirad;

import com.example.iem.cirad.Model.Manager.ParcelManager;
import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.Model.Pojo.User;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
//
//    public void insertionUser_isCorrect() throws Exception{
//
//        Parcel parcel = new Parcel(1,"Parcelle 12");
//        Parcel parcel1 = new Parcel(2,"Parcelle 22");
//
//        User user = new User(1,"mathieu","123",Boolean.FALSE);
//
//
//        Long parcelid = ParcelManager.getInstance(this).setParcel(parcel);
//        Long parcel1id = ParcelManager.getInstance(this).setParcel(parcel1);
//    }
}