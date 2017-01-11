package com.example.iem.cirad.Activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iem.cirad.Model.Manager.ParcelManager;
import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Exchanger;

public class MeasurementParcelActivity extends AppCompatActivity {

    private final Parcel SELECTION_ITEM = new Parcel(0,"Sélectionner la parcelle à traiter");

    ArrayList<Parcel> parcels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_parcel);

        setTitle("Nouvelle Saisie");

        final Spinner spinnerParcels = (Spinner)findViewById(R.id.spinnerParcels);


        parcels.add(SELECTION_ITEM);
        parcels.addAll(ParcelManager.getInstance(this).getParcels());
        ArrayList<String> parcelsName = new ArrayList<>();
        for (Parcel parcel : parcels ) {
            parcelsName.add(parcel.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,parcelsName);

        spinnerParcels.setAdapter(adapter);

        spinnerParcels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parcels.get(position).equals(SELECTION_ITEM)) {

                    Toast.makeText(getApplicationContext(),"into", Toast.LENGTH_LONG).show();
                    String test;
                    Parcel tt = parcels.get(position);

                } else {
                    Toast.makeText(getApplicationContext(),"l'autre", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}

