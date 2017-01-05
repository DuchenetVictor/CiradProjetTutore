package com.example.iem.cirad.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.iem.cirad.R;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setTitle("Tableau de bord");


        listviewParcel = (ListView)findViewById(R.id.listViewParcel);

        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_brightness_24px));
        map.put("parcel", "Parcelle 24");
        map.put("date", "12/12/2013");
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


        SimpleAdapter listviewadapter = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.dashboardadapter,
                new String[] {"img","parcel", "date"}, new int[] {R.id.imgViewParcel, R.id.txtViewNameParcel, R.id.txtViewDateParcel});

        listviewParcel.setAdapter(listviewadapter);

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
