package com.example.hospital_com;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetallesMedico extends AppCompatActivity {
    TextView lblNomDMV, lblApelDMV, lblNumIdDMV, lblEspDMV, lblFechaNacDMV, lblGeneroDMV;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_medico);

        lblNomDMV=findViewById(R.id.lblNomDMV);
        lblApelDMV=findViewById(R.id.lblApelDMV);
        lblNumIdDMV=findViewById(R.id.lblNumIdDMV);
        lblEspDMV=findViewById(R.id.lblEspDMV);
        lblFechaNacDMV=findViewById(R.id.lblFechaNacDMV);
        lblGeneroDMV=findViewById(R.id.lblGeneroDMV);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");


        lblNomDMV.setText("Nombres" + CrudMedicos.medicoArrayList.get(position).getNomMed());
        lblApelDMV.setText("Apellidos" + CrudMedicos.medicoArrayList.get(position).getApeMed());
        lblNumIdDMV.setText("Numero de Identificacion" + CrudMedicos.medicoArrayList.get(position).getIdMed());
        lblEspDMV.setText("Especialidad" + CrudMedicos.medicoArrayList.get(position).getIdEspMed());
        lblFechaNacDMV.setText("Fecha de Nacimiento" + CrudMedicos.medicoArrayList.get(position).getPassMed());
        lblGeneroDMV.setText("Genero" + CrudMedicos.medicoArrayList.get(position).getIdGenMed());


    }
}