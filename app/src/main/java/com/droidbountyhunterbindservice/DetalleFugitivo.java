package com.droidbountyhunterbindservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetalleFugitivo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_fugitivo);

        Intent intent = getIntent();
        cargarDetalle(intent.getIntExtra("position", 0));
    }

    public void cargarDetalle(int position){
        Fugitivo fugitivo = MainActivity.oListaFugitivos.get(position);

        TextView txtNombre = (TextView) findViewById(R.id.txtNombre);
        TextView txtEstatus = (TextView) findViewById(R.id.txtEstatus);
        TextView txtFoto = (TextView) findViewById(R.id.txtFoto);

        txtNombre.setText(fugitivo.getName());
        txtEstatus.setText(fugitivo.getStatus().equals("0")? "Fugitivo":"Atrapado");
        String foto = fugitivo.getPhoto();
        txtFoto.setText(foto.equals("")? "No cuenta con fotografía":foto);


    }
}
