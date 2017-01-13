package com.example.iem.cirad.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.iem.cirad.Controller.MySQLite;
import com.example.iem.cirad.Model.Manager.ActionManager;
import com.example.iem.cirad.Model.Manager.MeasurementManager;
import com.example.iem.cirad.Model.Manager.ParcelManager;
import com.example.iem.cirad.Model.Manager.UserManager;
import com.example.iem.cirad.Model.Pojo.Action;
import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.Model.Pojo.User;
import com.example.iem.cirad.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class dashBoardActivity extends AppCompatActivity {

    private ListView listviewParcel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        setTitle("Tableau de bord");


        Parcel parcel = new Parcel(1, "Parcelle NotSynch", "dd", "dd");
        Parcel parcelSynch = new Parcel(2, "Parcelle Synch");
        Parcel parcel3 = new Parcel(3, "Parcelle 3");
        Parcel parcel4 = new Parcel(4, "Parcelle 4");
        Parcel parcel5 = new Parcel(5, "Parcelle 5");
        Parcel parcel6 = new Parcel(6, "Parcelle 6");
        ParcelManager.getInstance(this).setParcel(parcel);
        ParcelManager.getInstance(this).setParcel(parcelSynch);
        ParcelManager.getInstance(this).setParcel(parcel3);
        ParcelManager.getInstance(this).setParcel(parcel4);
        ParcelManager.getInstance(this).setParcel(parcel5);
        ParcelManager.getInstance(this).setParcel(parcel6);



        User user = new User(1, "mathieu", "123", false,true);

        UserManager.getInstance(this).setUser(user);


        Date date = new Date(213132121);
        Action action1 = new Action("labourer", 1, Boolean.FALSE, "sdfdbfq,fqsdf", date, 1);
        Action action2 = new Action("TRaitement anti fongique", 3, Boolean.TRUE, 0, "ce dépecher de faire le traitemzent", date, 1);
        Action action3 = new Action("TRaitement anti clement", 3, Boolean.TRUE, 0, "ce dépecher de faire le traitemzent", date, 1);

        action1.setId((int) ActionManager.getInstance(this).setAction(action1));
        action2.setId((int) ActionManager.getInstance(this).setAction(action2));
        action3.setId((int) ActionManager.getInstance(this).setAction(action3));
        ArrayList<Action> actionsnotSynch = new ArrayList<>();

        actionsnotSynch.add(action1);
        actionsnotSynch.add(action2);
        actionsnotSynch.add(action3);
        MeasurementManager.getInstance(this).setMeasure(actionsnotSynch, parcel);


        ArrayList<Action> actionsSynch = new ArrayList<>();
        actionsSynch.add(action1);
        MeasurementManager.getInstance(this).setMeasure(actionsSynch,parcelSynch);

        ArrayList<Action> atciontest = MeasurementManager.getInstance(this).getActionsInParcel(parcelSynch,Boolean.FALSE);



        MeasurementManager.getInstance(this).updateMeasurementSynchro(parcelSynch);


        ArrayList<Action> actionsinparcelNotSynch = MeasurementManager.getInstance(this).getActionsInParcel(parcel, Boolean.FALSE);
        ArrayList<Action> actionsinparcelSynch = MeasurementManager.getInstance(this).getActionsInParcel(parcelSynch, Boolean.TRUE);


        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        ArrayList<Parcel> parcels = new ArrayList<>();
        parcels = MeasurementManager.getInstance(this).getParcelsBySynchro(Boolean.FALSE);

        for (Parcel parcelNotSynchro : parcels) {
            map = new HashMap<String, String>();
            map.put("img", String.valueOf(R.drawable.ic_notsynch));
            map.put("parcel", parcelNotSynchro.getName());
            map.put("date", String.valueOf(MeasurementManager.getInstance(this).getLastDateInMeasurement(parcelNotSynchro, Boolean.FALSE)));
            listItem.add(map);
        }

        parcels = MeasurementManager.getInstance(this).getParcelsBySynchro(Boolean.TRUE);
        for (Parcel parcelSynchro : parcels) {
            map = new HashMap<String, String>();
            map.put("img", String.valueOf(R.drawable.ic_synch));
            map.put("parcel", parcelSynchro.getName());
            map.put("date", String.valueOf(MeasurementManager.getInstance(this).getLastDateInMeasurement(parcelSynchro, Boolean.TRUE)));
            listItem.add(map);
        }


        listviewParcel = (ListView) findViewById(R.id.listViewParcel);
        SimpleAdapter listviewadapter = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.dashboardadapter,
                new String[]{"img", "parcel", "date"}, new int[]{R.id.imgViewParcel, R.id.txtViewNameParcel, R.id.txtViewDateParcel});

        listviewParcel.setAdapter(listviewadapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), MeasurementParcelActivity.class);
                startActivity(myIntent);
            }
        });



        listviewParcel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

// TODO: 10/01/2017 faire le click sur un item
                ArrayList<String> array = new ArrayList<>();
                Intent myIntent = new Intent(getApplicationContext(), detailsActionActivity.class);

                myIntent.putExtra("key","");

                startActivity(myIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

           User user = UserManager.getInstance(this).GetUserConnected();
            user.setIsConnected(false);
            //UserManager.getInstance(this).SetISConnectedForUser(user);
            Intent i = new Intent(this, LoginActivity.class);
            this.startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
