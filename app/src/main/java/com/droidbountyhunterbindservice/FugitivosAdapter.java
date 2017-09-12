package com.droidbountyhunterbindservice;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by gcoronad on 12/09/2017.
 */

public class FugitivosAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Fugitivo> fugitivos;

    public FugitivosAdapter(Activity activity, ArrayList<Fugitivo> fugitivos) {
        this.activity = activity;
        this.fugitivos = fugitivos;
    }

    @Override
    public int getCount() {
        if (fugitivos != null)
            return fugitivos.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (fugitivos != null)
            return fugitivos.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View oVista = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            oVista = inf.inflate(R.layout.listfugitivos, null);
        }

        TextView txtFugitivo = (TextView) oVista.findViewById(R.id.nombreUsuario);

        txtFugitivo.setText(fugitivos.get(i).getName());



        return oVista;
    }
}
