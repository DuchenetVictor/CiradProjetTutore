package com.example.iem.cirad.Activity;

import android.content.Intent;
import android.net.NetworkInfo;
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


        Parcel parcel = new Parcel(1,"Parcelle 12","dd","dd");
        Parcel parcel1 = new Parcel(2,"Parcelle 22");
        User user = new User(1,"mathieu","123",Boolean.FALSE);

//        public Action(String name, int emergencyLevel, boolean isTreatment, int treatmentLevel, String remark, Date dateMeasure, int idParcel, int idUser) {
//        public Action(String name, int emergencyLevel, boolean isTreatment, String remark, Date dateMeasure, int idParcel, int idUser) {
//        public Action(int id, String name, int emergencyLevel, boolean isTreatment, int treatmentLevel, String remark, Date dateMeasure, int idParcel, int idUser) {

        Date date = new Date(213132121);
        Action action1 = new Action(1,"labourer",1,Boolean.FALSE,"sdfdbfq,fqsdf",date,1,1);

        Action action2 = new Action(2,"TRaitement anti fongique",3,Boolean.TRUE,0,"ce dépecher de faire le traitemzent",date,1,1);
        Action action3 = new Action(3,"TRaitement anti clement",3,Boolean.TRUE,0,"ce dépecher de faire le traitemzent",date,1,1);


        Long parcelid = ParcelManager.getInstance(this).setParcel(parcel);
        Long parcel1id = ParcelManager.getInstance(this).setParcel(parcel1);
        Long userid = UserManager.getInstance(this).setUser(user);
         ArrayList<Action> actionssss = new ArrayList<>();
        actionssss.add(action1);
        actionssss.add(action2);
        actionssss.add(action3);
        MeasurementManager.getInstance(this).setMeasure(actionssss,parcel);
        ArrayList<Action> actionsinparcel = MeasurementManager.getInstance(this).getActionsInParcel(parcel);








        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        ArrayList<Parcel> parcels = new ArrayList<>();
        parcels = MeasurementManager.getInstance(this).getParcelsBySynchro(Boolean.FALSE);
//
//        for (Parcel parcelNotSynchro : parcels) {
//
//        }

        map = new HashMap<String, String>();
        listItem.add(map);
        map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_brightness_24px));
        map.put("parcel", "Parcelle 14");
        map.put("date", "14/14/2014");
        listItem.add(map);
        map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_brightness_24px));
        map.put("parcel", "Parcelle 1");
        map.put("date", "01/01/2001");
        listItem.add(map); map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_brightness_24px));
        map.put("parcel", "Parcelle 1");
        map.put("date", "01/01/2001");
        listItem.add(map); map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_brightness_24px));
        map.put("parcel", "Parcelle 1");
        map.put("date", "01/01/2001");
        listItem.add(map); map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_brightness_24px));
        map.put("parcel", "Parcelle 1");
        map.put("date", "01/01/2001");
        listItem.add(map); map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_brightness_24px));
        map.put("parcel", "Parcelle 1");
        map.put("date", "01/01/2001");
        listItem.add(map); map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_brightness_24px));
        map.put("parcel", "Parcelle 1");
        map.put("date", "01/01/2001");
        listItem.add(map); map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_brightness_24px));
        map.put("parcel", "Parcelle 1");
        map.put("date", "01/01/2001");
        listItem.add(map);

        listviewParcel = (ListView)findViewById(R.id.listViewParcel);
        SimpleAdapter listviewadapter = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.dashboardadapter,
                new String[] {"img","parcel", "date"}, new int[] {R.id.imgViewParcel, R.id.txtViewNameParcel, R.id.txtViewDateParcel});

        listviewParcel.setAdapter(listviewadapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Action action = new Action();
                ArrayList<String> array = new ArrayList<>();
                Intent myIntent = new Intent(getApplicationContext(), detailsActionActivity.class);

                myIntent.putExtra("key", action.inArray());

                startActivity(myIntent);
            }
        });

        listviewParcel.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                //TODO
                Action action = new Action();
                action.setRemark("sssss");
                String test ;
                String toto = "d";
                test = toto;

               /* ArrayList<String> array = new ArrayList<>();
                Intent myIntent = new Intent(getApplicationContext(), detailsActionActivity.class);

                myIntent.putExtra("key", action.inArray());

                startActivity(myIntent);*/
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
