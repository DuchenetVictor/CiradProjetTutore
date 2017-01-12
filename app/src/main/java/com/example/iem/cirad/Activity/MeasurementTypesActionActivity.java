package com.example.iem.cirad.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iem.cirad.Activity.Adapter.AdapterModel;
import com.example.iem.cirad.Activity.Adapter.MeasurementTypesActionAdapter;
import com.example.iem.cirad.Model.Manager.MeasurementManager;
import com.example.iem.cirad.Model.Manager.ParcelManager;
import com.example.iem.cirad.Model.Manager.TypeActionManager;
import com.example.iem.cirad.Model.Manager.UserManager;
import com.example.iem.cirad.Model.Pojo.Action;
import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.Model.Pojo.TypeAction;
import com.example.iem.cirad.Model.Pojo.User;
import com.example.iem.cirad.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MeasurementTypesActionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_types_action);
        setTitle("Nouvelle Saisie");


/*        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;*/

        String idParcel = getIntent().getExtras().getString("key");
        User user = UserManager.getInstance(this).GetUserConnected();

        Parcel parcel = ParcelManager.getInstance(this).getParcelById(Integer.valueOf(idParcel));
        ArrayList<Action> actions = MeasurementManager.getInstance(this).getActionsInParcel(parcel,Boolean.FALSE);


        ArrayList<String> allTypesAction = TypeActionManager.getInstance(this).getTypesAction();
        ArrayList<AdapterModel> adapterModels= new ArrayList<>();

        for (int i = 1; i< allTypesAction.size();i++){
            adapterModels.add(new AdapterModel(i,allTypesAction.get(i),Boolean.FALSE));
        }

        /*Test*/
        adapterModels.add(new AdapterModel(0,allTypesAction.get(0),Boolean.TRUE));

        TextView txtvParcelName = (TextView)findViewById(R.id.txtvParcelName);
        txtvParcelName.setText(parcel.getName());

/*

        for (String typeaction : typesAction) {
            map = new HashMap<String, String>();
            map.put("btnName",">>");
            map.put("typeAction", typeaction);
            listItem.add(map);
        }


 SimpleAdapter listviewadapter = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.typeactionadaptater,
                new String[]{"btnName","typeAction"}, new int[]{R.id.btnConfigAction,R.id.txtvTypeActionName});

        lstvTypesAction.setAdapter(listviewadapter);*/

        ListView lstvTypesAction = (ListView) findViewById(R.id.listvTypesAction);
        ArrayAdapter<AdapterModel> adapter = new MeasurementTypesActionAdapter(this,adapterModels);
        lstvTypesAction.setAdapter(adapter);

        }
}
