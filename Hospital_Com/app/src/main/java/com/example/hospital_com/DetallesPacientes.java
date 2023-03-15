package com.example.hospital_com;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetallesPacientes extends AppCompatActivity {
    TextView lblNomDPV, lblApelDPV, lblNumIdDPV, lblFechaNacDPV, lblGeneroDPV, lblRHDPV;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pacientes);

        lblNomDPV=findViewById(R.id.lblNomDPV);
        lblApelDPV=findViewById(R.id.lblApellidoDPV);
        lblNumIdDPV=findViewById(R.id.lblNumIdDPV);
        lblFechaNacDPV=findViewById(R.id.lblFechaNacDPV);
        lblGeneroDPV=findViewById(R.id.lblGeneroDPV);
        lblRHDPV=findViewById(R.id.lblRhDPV);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        lblNomDPV.setText("Nombres" + CrudPacientes.pacienteArrayList.get(position).getNomPa());
        lblApelDPV.setText("Apellido" + CrudPacientes.pacienteArrayList.get(position).getApePa());
        lblNumIdDPV.setText("Numero de Identificacion" + CrudPacientes.pacienteArrayList.get(position).getIdPa());
        lblFechaNacDPV.setText("Fecha de Nacimiento" + CrudPacientes.pacienteArrayList.get(position).getFechaNPa());
        lblGeneroDPV.setText("Genero" + CrudPacientes.pacienteArrayList.get(position).getIdGenPa());
        lblRHDPV.setText("RH" + CrudPacientes.pacienteArrayList.get(position).getIdRhPa());


    }
}