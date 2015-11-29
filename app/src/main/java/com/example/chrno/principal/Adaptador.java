package com.example.chrno.principal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chrno on 28/11/2015.
 */
public class Adaptador extends ArrayAdapter<String> {

    private Context ctx;
    private int res;
    private LayoutInflater lInflator;
    private ArrayList<String> values;

    static class ViewHolder {
        TextView tv;
    }

    public Adaptador(Context context,ArrayList<String> objects) {
        super(context, R.layout.item, objects);
        this.ctx = context;
        this.res = R.layout.item;
        this.lInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.values = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = new ViewHolder();

        if (convertView == null) {
            convertView = lInflator.inflate(res, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tvEnlace);
            vh.tv = tv;

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(values.get(position));

        return convertView;
    }
}
