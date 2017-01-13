package com.example.iem.cirad.Activity.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import com.example.iem.cirad.Activity.MeasurementParcelActivity;
import com.example.iem.cirad.Activity.detailsActionActivity;
import com.example.iem.cirad.R;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by iem on 12/01/2017.
 */
 class ViewHolder {
    protected Button btnConfigAction;
    protected CheckBox chkSelecAction;
    protected TextView txtNameAction;

}
//todo http://stackoverflow.com/questions/12602426/listview-with-checkbox-radiobutton-textview-and-button-not-working-correctly-in

public class MeasurementTypesActionAdapter extends ArrayAdapter<AdapterModel> {


    private final List<AdapterModel> listadaptersModel;
    private final Activity context;

    public MeasurementTypesActionAdapter(Activity context, List<AdapterModel> listadaptersModel) {
        super(context, R.layout.typeactionadaptater, listadaptersModel);
        this.context = context;
        this.listadaptersModel = listadaptersModel;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.typeactionadaptater, null);

            viewHolder = new ViewHolder();

            viewHolder.btnConfigAction = (Button) convertView.findViewById(R.id.btnConfigAction);
            viewHolder.btnConfigAction.setTag(position);

            viewHolder.txtNameAction = (TextView)convertView.findViewById(R.id.txtvTypeActionName);

            viewHolder.chkSelecAction = (CheckBox) convertView.findViewById(R.id.chkbSelect);
            viewHolder.chkSelecAction.setTag(position);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtNameAction.setText(listadaptersModel.get(position).getName());

        viewHolder.btnConfigAction.setText(">>");
        viewHolder.btnConfigAction.setTag(position);

        viewHolder.chkSelecAction.setChecked(listadaptersModel.get(position).isSelected());
        viewHolder.chkSelecAction.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckBox checkbox = (CheckBox) v;
                int getPosition = (Integer) checkbox.getTag();
                listadaptersModel.get(getPosition).setSelected(checkbox.isChecked());
            }
        });

        viewHolder.btnConfigAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button)v;
                Integer indice = (Integer) btn.getTag();
                Intent myIntent = new Intent(context, detailsActionActivity.class);

                myIntent.putExtra("userid",String.valueOf(listadaptersModel.get(position).getUser().getId()));
                myIntent.putExtra("key",String.valueOf(listadaptersModel.get(position).getName()));
                myIntent.putExtra("parcelid",String.valueOf(listadaptersModel.get(position).getParcel().getId()));

                getContext().startActivity(myIntent);
            }
        });

        return convertView;
    }


}
