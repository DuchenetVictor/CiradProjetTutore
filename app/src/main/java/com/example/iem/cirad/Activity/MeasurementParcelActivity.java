package com.example.iem.cirad.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.iem.cirad.Model.Manager.TypeActionManager;
import com.example.iem.cirad.R;

import java.util.ArrayList;

public class MeasurementParcelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_parcel);

        setTitle("Nouvelle Saisie");

        Spinner spinnerParcels = (Spinner)findViewById(R.id.spinnerParcels);

        ArrayList<String> typesaction = new ArrayList<>();
        typesaction.add("traitement anti mouche");
        typesaction.add("testttttt");
        typesaction.add("anti-marti");
        typesaction.add("engrais");
        typesaction.add("Parcelle 11");
        typesaction.add("Parcelle 4 8 15 16 23 42");

        TypeActionManager.getInstance(this).setTypesAction(typesaction);

        ArrayList<String> test = TypeActionManager.getInstance(this).getTypesAction();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, test);

        spinnerParcels.setAdapter(adapter);
    }
}
