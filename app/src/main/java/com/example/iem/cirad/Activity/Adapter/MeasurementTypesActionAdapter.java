package com.example.iem.cirad.Activity.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;


import com.example.iem.cirad.R;

import java.util.List;

/**
 * Created by iem on 12/01/2017.
 */
 class ViewHolder {
    protected Button btnConfigAction;
    protected CheckBox chkSelecAction;

}
//todo http://stackoverflow.com/questions/12602426/listview-with-checkbox-radiobutton-textview-and-button-not-working-correctly-in

public class MeasurementTypesActionAdapter extends ArrayAdapter<AdapterModel> {


    private final List<AdapterModel> listTypeAction;
    private final Activity context;
    boolean checkAll_flag = false;
    boolean checkItem_flag = false;

    public MeasurementTypesActionAdapter(Activity context, List<AdapterModel> listTypeAction) {
        super(context, R.layout.typeactionadaptater, listTypeAction);
        this.context = context;
        this.listTypeAction = listTypeAction;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.typeactionadaptater, null);

            viewHolder = new ViewHolder();
            viewHolder.btnConfigAction = (Button) convertView.findViewById(R.id.btnConfigAction);
            viewHolder.btnConfigAction.setTag(position);
            viewHolder.chkSelecAction = (CheckBox) convertView.findViewById(R.id.chkbSelect);
            viewHolder.chkSelecAction.setTag(position);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.btnConfigAction.setText(listTypeAction.get(position).toString());
        viewHolder.chkSelecAction.setChecked(listTypeAction.get(position).isSelected());
        viewHolder.chkSelecAction.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckBox checkbox = (CheckBox) v;
                int getPosition = (Integer) checkbox.getTag();
                listTypeAction.get(getPosition).setSelected(checkbox.isChecked());
            }
        });
        return convertView;
    }
}
