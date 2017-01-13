package com.example.iem.cirad.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.iem.cirad.Model.Manager.ActionManager;
import com.example.iem.cirad.Model.Manager.MeasurementManager;
import com.example.iem.cirad.Model.Manager.ParcelManager;
import com.example.iem.cirad.Model.Pojo.Action;
import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.Model.Pojo.User;
import com.example.iem.cirad.R;
import com.example.iem.cirad.rest.ApiClient;


import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.util.ArrayList;


public class detailsActionActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    TextView txtvTypeActionName;
    SeekBar skbtraitement;
    SeekBar skbEmergency;
    int posUrgence;
    int posTraitement;
    String typeAction;
    int userid;
    int parcelid;
    Parcel parcel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_details_action);

        setContentView(R.layout.activity_details_action);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dispatchTakePictureIntent();
        validationAction();
        seekBarUrgence();

        seekBarNivTraitement();


        typeAction = getIntent().getExtras().getString("key");
        userid = Integer.valueOf(getIntent().getExtras().getString("userid"));
        parcelid = Integer.valueOf(getIntent().getExtras().getString("parcelid"));
        parcel = ParcelManager.getInstance(this).getParcelById(parcelid);

        txtvTypeActionName= (TextView)findViewById(R.id.txtvActionName);
        txtvTypeActionName.setText(typeAction);

    }


    private void dispatchTakePictureIntent(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnPicture);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }


    private void validationAction(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnOk);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// TODO: 13/01/2017 isTraitement pas geré
                TextView txtv = (TextView)findViewById(R.id.txtvRemark);
                Date date= new Date(1111111111);
                Action action = new Action(typeAction,posUrgence,Boolean.TRUE,posTraitement,txtv.getText().toString(),date,userid);

                action.setId((int)ActionManager.getInstance(getApplicationContext()).setAction(action));
                Intent myIntent = new Intent(getApplicationContext(),MeasurementTypesActionActivity.class);
                ArrayList<Action> actions = new ArrayList<Action>();
                actions.add(action);

                MeasurementManager.getInstance(getApplicationContext()).setMeasure(actions,parcel);
                myIntent.putExtra("key",String.valueOf(parcelid));
                startActivity(myIntent);


            }
        });
    }
    private void seekBarUrgence(){
        SeekBar sbLvlAlerte = (SeekBar) findViewById(R.id.sbLvlAlerte);
        final TextView tvLvlAlert = (TextView) findViewById(R.id.tvLvlAlert);;
        tvLvlAlert.setText("1 (normal)" );

        sbLvlAlerte.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 1;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                if(progresValue == 0)
                {
                    tvLvlAlert.setText( progresValue + " (peu urgent)" );
                    posUrgence = progresValue;
                }
                else if(progresValue == 1)
                {
                    tvLvlAlert.setText( progresValue + " (normal)" );
                    posUrgence = progresValue;
                }
                else
                {
                    tvLvlAlert.setText( progresValue + " (très urgent)" );
                    posUrgence = progresValue;
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    private void seekBarNivTraitement(){
        SeekBar sbLvlTreatment = (SeekBar) findViewById(R.id.sbLvlTreatment);
        final TextView tvLvlTreatment = (TextView) findViewById(R.id.tvLvlTreatment);

        tvLvlTreatment.setText("1 (normal)");

        sbLvlTreatment.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 1;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                if (progresValue == 0) {
                    tvLvlTreatment.setText(progresValue + " (peu intensif)");
                    posTraitement = progresValue;

                } else if (progresValue == 1) {
                    tvLvlTreatment.setText(progresValue + " (normal)");
                    posTraitement = progresValue;
                } else {
                    tvLvlTreatment.setText(progresValue + " (très intensif)");
                    posTraitement = progresValue;

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.pictureContainer);

            ImageView ivPicture = new ImageView(detailsActionActivity.this);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(5,0,10,0);
            ivPicture.setLayoutParams(lp);

            ivPicture.setImageBitmap(imageBitmap);
            linearLayout.addView(ivPicture);



            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.
            byte [] byte_arr = stream.toByteArray();
            String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);

        }
    }

}
