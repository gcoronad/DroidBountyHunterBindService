package com.droidbountyhunterbindservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Fugitivo> oListaFugitivos = new ArrayList<>();

    public void mostrarListadoFugitivos(View view) {
        Intent intent = new Intent(this, ListadoFugitivosActivity.class);
        startActivity(intent);
    }

    class ResponseHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Bundle bundle = msg.getData();
                    ArrayList<Integer> ids = bundle.getIntegerArrayList("ids");
                    ArrayList<CharSequence> nombres = bundle.getCharSequenceArrayList("nombres");
                    ArrayList<CharSequence> status = bundle.getCharSequenceArrayList("status");
                    ArrayList<CharSequence> fotos = bundle.getCharSequenceArrayList("fotos");



                    for(int i = 0; i < ids.size(); i++){
                        int id = ids.get(i);
                        String nombre = nombres.get(i).toString();
                        String fstatus = status.get(i).toString();
                        String foto = fotos.get(i).toString();

                        oListaFugitivos.add(new Fugitivo(id, nombre, fstatus, foto));
                    }
                    Toast.makeText(MainActivity.this, "Fugitivos cargados", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }


    Messenger mService = null;
    boolean mBound;

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = new Messenger(service);
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            mService = null;
            mBound = false;
        }
    };

    public void conexion(View v) {
        if (!mBound) return;
        Message msg = Message.obtain(null, 1, 0, 0);
        msg.replyTo = new Messenger(new ResponseHandler());

        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent();
        intent.setPackage("training.edu.droidbountyhunter");
        intent.setAction("training.edu.CONSUMIR_SERVICIO");


        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }
}
