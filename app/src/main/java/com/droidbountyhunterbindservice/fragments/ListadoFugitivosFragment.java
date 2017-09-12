package com.droidbountyhunterbindservice.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.droidbountyhunterbindservice.DetalleFugitivo;
import com.droidbountyhunterbindservice.FugitivosAdapter;
import com.droidbountyhunterbindservice.MainActivity;
import com.droidbountyhunterbindservice.R;


public class ListadoFugitivosFragment extends Fragment {

    private boolean isTablet = false;
    private OnListListener listener;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isTablet = getActivity().getSupportFragmentManager().findFragmentById(R.id.detalleFugitivosFragment) != null;
        if(isTablet){
            listener = (OnListListener) getActivity().getSupportFragmentManager().findFragmentById(R.id.detalleFugitivosFragment);
        }
    }


    public ListadoFugitivosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_fugitivos, container, false);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        cargarFugitivos();
    }

    public void cargarFugitivos() {
        if (MainActivity.oListaFugitivos.size() > 0) {
            final FugitivosAdapter adapter = new FugitivosAdapter(getActivity(), MainActivity.oListaFugitivos);
            ListView list = (ListView) getView().findViewById(R.id.list);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    cargarDetalle(position);
                }
            });
        }
    }

    public void cargarDetalle(int position) {
        if (isTablet) {
            listener.onFugitivoItemList(position);
        } else {
            Intent intent = new Intent(getActivity(), DetalleFugitivo.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }


}
