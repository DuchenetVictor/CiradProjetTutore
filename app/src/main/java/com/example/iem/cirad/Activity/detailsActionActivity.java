package com.example.iem.cirad.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iem.cirad.R;

public class detailsActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_action);
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
        setTitle(R.string.title_activity_details_action);
        seekBarUrgence();
        seekBarNivTraitement();
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
                }
                else if(progresValue == 1)
                {
                    tvLvlAlert.setText( progresValue + " (normal)" );
                }
                else
                {
                    tvLvlAlert.setText( progresValue + " (très urgent)" );
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
        final TextView tvLvlTreatment = (TextView) findViewById(R.id.tvLvlTreatment);;
        tvLvlTreatment.setText("1 (normal)" );

        sbLvlTreatment.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 1;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                if(progresValue == 0)
                {
                    tvLvlTreatment.setText( progresValue + " (peu intensif)" );
                }
                else if(progresValue == 1)
                {
                    tvLvlTreatment.setText( progresValue + " (normal)" );
                }
                else
                {
                    tvLvlTreatment.setText( progresValue + " (très intensif)" );
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



}
