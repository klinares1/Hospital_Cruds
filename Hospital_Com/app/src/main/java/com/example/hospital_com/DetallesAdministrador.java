package com.example.hospital_com;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetallesAdministrador extends AppCompatActivity {
    TextView lblNomDAV, lblApeDAV, lblNumIdDAV;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_administrador);

        lblNomDAV=findViewById(R.id.lblNomDAV);
        lblApeDAV=findViewById(R.id.lblApeDAV);
        lblNumIdDAV=findViewById(R.id.lblNumIdDAV);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        lblNomDAV.setText("Nombres" + CrudAdministrados.administradorArrayList.get(position).getNomAd());
        lblApeDAV.setText("Apellidos" + CrudAdministrados.administradorArrayList.get(position).getApeAd());
        lblNumIdDAV.setText("Numero de Identificacion" + CrudAdministrados.administradorArrayList.get(position).getIdAd());

    }
}