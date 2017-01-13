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
import android.widget.Button;
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
    private Button btnSynch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnSynch=(Button)findViewById(R.id.btnSynchParcel);


        setTitle("Tableau de bord");

        User userconnected = UserManager.getInstance(this).GetUserConnected();



        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        ArrayList<Parcel> parcels = new ArrayList<>();

        ArrayList<Parcel> parcelsNotSync = MeasurementManager.getInstance(this).getParcelsBySynchroAndUser(Boolean.FALSE,userconnected);

        for (Parcel parcelNotSync : parcelsNotSync) {
            map = new HashMap<String, String>();
            map.put("img", String.valueOf(R.drawable.ic_notsynch));
            map.put("parcel", parcelNotSync.getName());
            map.put("date", String.valueOf(MeasurementManager.getInstance(this).getLastDateInMeasurement(parcelNotSync, Boolean.FALSE)));
            listItem.add(map);
        }

        ArrayList<Parcel> parcelsSync = MeasurementManager.getInstance(this).getParcelsBySynchroAndUser(Boolean.TRUE,userconnected);

        for (Parcel parcelSync : parcelsSync) {
            map = new HashMap<String, String>();
            map.put("img", String.valueOf(R.drawable.ic_synch));
            map.put("parcel", parcelSync.getName());
            map.put("date", String.valueOf(MeasurementManager.getInstance(this).getLastDateInMeasurement(parcelSync, Boolean.TRUE)));
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
               /* ArrayList<String> array = new ArrayList<>();
                Intent myIntent = new Intent(getApplicationContext(), detailsActionActivity.class);

                myIntent.putExtra("key","");

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
            Toast.makeText(this, "This is my Toast message!",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
