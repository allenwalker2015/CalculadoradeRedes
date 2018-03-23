package com.allen.calculadoraderedes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText clientip,clientsubnet,totalips,clientips,broadcast,netid, mask;
    private IPValidator validador = new IPValidator();
    private OperacionesNet op = new OperacionesNet();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clientip =  findViewById(R.id.clientip);
        clientsubnet = findViewById(R.id.clientsubnet);
        totalips = findViewById(R.id.totalipsinput);
        clientips = findViewById(R.id.clientipsinput);
        broadcast = findViewById(R.id.broadcastinput);
        netid = findViewById(R.id.netidinput);
        mask = findViewById(R.id.mascarainput);
    }


    public void calcular(View v) {
        String ip= clientip.getText().toString();
        String subnet = clientsubnet.getText().toString();
        Log.d("IP", "calcular: "+ ip);
        if(validador.validate(ip) && Integer.parseInt(subnet)<=32 && Integer.parseInt(subnet)>=0){
            String[] res = op.generateIPs(ip,subnet);
            netid.setText(res[0]);
            broadcast.setText(res[1]);
            totalips.setText(res[2]);
            clientips.setText(res[3]);
            mask.setText(res[4]);
            Log.d("IP", "LA IP ES VALIDA: "+ ip);
        }
    }
}
