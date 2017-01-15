package com.example.iem.cirad.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iem.cirad.Activity.Adapter.AdapterModel;
import com.example.iem.cirad.Activity.Adapter.MeasurementTypesActionAdapter;
import com.example.iem.cirad.Model.Manager.MeasurementManager;
import com.example.iem.cirad.Model.Manager.ParcelManager;
import com.example.iem.cirad.Model.Manager.TypeActionManager;
import com.example.iem.cirad.Model.Manager.UserManager;
import com.example.iem.cirad.Model.Pojo.Action;
import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.Model.Pojo.User;
import com.example.iem.cirad.R;

import java.util.ArrayList;

public class MeasurementTypesActionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_types_action);
        setTitle("Nouvelle Saisie");


        String idParcel = getIntent().getExtras().getString("key");
        User user = UserManager.getInstance(this).GetUserConnected();

        Parcel parcel = ParcelManager.getInstance(this).getParcelById(Integer.valueOf(idParcel));

        ArrayList<String> typesAction = TypeActionManager.getInstance(this).getTypesAction();

        ArrayList<AdapterModel> adapterModels = new ArrayList<>();
        for (int i = 0; i< typesAction.size();i++){
            adapterModels.add(new AdapterModel(i,typesAction.get(i),Boolean.FALSE,user,parcel));
        }

        ArrayList<Action> actions = MeasurementManager.getInstance(this).getActionsInParcelByUser(parcel,Boolean.FALSE,user);

        //on passe la checkebox a selectionner a chaque correspondence entre name de adaptermodel(le typeaction)
        // et les Actions deja renseigner par le ser sur cette parcel
        //// TODO: 13/01/2017
    /*    if(!actions.isEmpty()){
            for (int i = 0;i<typesAction.size();i++ ){
                for (AdapterModel adapterModel : adapterModels){
                    if (adapterModel.getName().contains(actions.get(i).getName())){
                        adapterModels.get(i).setSelected(Boolean.TRUE);
                        adapterModels.get(i).setId(actions.get(i).getId());
                    }
                }
            }
        }*/

        TextView txtvParcelName = (TextView)findViewById(R.id.txtvParcelName);
        txtvParcelName.setText(parcel.getName());

        ListView lstvTypesAction = (ListView) findViewById(R.id.listvTypesAction);
        ArrayAdapter<AdapterModel> adapter = new MeasurementTypesActionAdapter(this,adapterModels);
        lstvTypesAction.setAdapter(adapter);
        //// TODO: 15/01/2017 faire le bouton, qui  recupere toutes les actions checker

        //// TODO: 15/01/2017 faire a chaque click sur un action, reouvrir l'action avec les données deja rempli si action est dans la bdd (recupe l'id de l'action.
        }
}
