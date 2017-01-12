package com.example.iem.cirad.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iem.cirad.Model.Manager.ParcelManager;
import com.example.iem.cirad.Model.Manager.TypeActionManager;
import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MeasurementTypesActionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_types_action);

        setTitle("Nouvelle Saisie");


        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        String idParcel = getIntent().getExtras().getString("key");

        Parcel parcel = ParcelManager.getInstance(this).getParcelById(Integer.valueOf(idParcel));

        TextView txtvParcelName = (TextView)findViewById(R.id.txtvParcelName);
        txtvParcelName.setText(parcel.getName());

        ArrayList<String> pleindetypesAction= new ArrayList<>();
        pleindetypesAction.add("Traitement");
        pleindetypesAction.add("desherbage");
        pleindetypesAction.add("anti marti");
        pleindetypesAction.add("de-clementisation");

        TypeActionManager.getInstance(this).setTypesAction(pleindetypesAction);

        ArrayList<String> typesAction = TypeActionManager.getInstance(this).getTypesAction();

        for (String typeaction : typesAction) {
            map = new HashMap<String, String>();
            map.put("btnName",">>");
            map.put("typeAction", typeaction);
            listItem.add(map);
        }


        ListView lstvTypesAction = (ListView) findViewById(R.id.listvTypesAction);
        SimpleAdapter listviewadapter = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.typeactionadaptater,
                new String[]{"btnName","typeAction"}, new int[]{R.id.btnConfigAction,R.id.txtvTypeActionName});

        lstvTypesAction.setAdapter(listviewadapter);

        lstvTypesAction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT);
            }
        });

    }
}
