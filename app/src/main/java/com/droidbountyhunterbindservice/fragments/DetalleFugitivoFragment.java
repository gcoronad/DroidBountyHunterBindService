package com.droidbountyhunterbindservice.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.droidbountyhunterbindservice.Fugitivo;
import com.droidbountyhunterbindservice.MainActivity;
import com.droidbountyhunterbindservice.R;


public class DetalleFugitivoFragment extends Fragment implements  OnListListener {


    public DetalleFugitivoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_fugitivo, container, false);
    }

    public void cargarDetalle(int position){
        Fugitivo fugitivo = MainActivity.oListaFugitivos.get(position);

        TextView txtNombre = (TextView) getView().findViewById(R.id.txtNombre);
        TextView txtEstatus = (TextView) getView().findViewById(R.id.txtEstatus);
        TextView txtFoto = (TextView) getView().findViewById(R.id.txtFoto);

        txtNombre.setText(fugitivo.getName());
        txtEstatus.setText(fugitivo.getStatus().equals("0")? "Fugitivo":"Atrapado");
        String foto = fugitivo.getPhoto();
        txtFoto.setText(foto.equals("")? "No cuenta con fotografía":foto);


    }

    @Override
    public void onFugitivoItemList(int position) {
        cargarDetalle(position);
    }
}
