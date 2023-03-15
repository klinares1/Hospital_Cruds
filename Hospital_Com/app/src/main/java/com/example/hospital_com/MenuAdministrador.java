package com.example.hospital_com;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuAdministrador extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> AdminList = new ArrayList<>();
    ArrayAdapter<String> AdminAdapter;
    ArrayList<String> PacienteList = new ArrayList<>();
    ArrayAdapter<String> PacienteAdapter;
    ArrayList<String> MedicoList = new ArrayList<>();
    ArrayAdapter<String> MedicoAdapter;
    ArrayList<String> CitaList = new ArrayList<>();
    ArrayAdapter<String> CitaAdapter;
    Spinner spnAdminMA,spnPacienteMA,spnMedicoMA,spnCitaMA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrador);

        spnAdminMA = findViewById(R.id.spnAdminMA);
        spnPacienteMA = findViewById(R.id.spnPacienteMA);
        spnMedicoMA = findViewById(R.id.spnMedicoMA);
        spnCitaMA = findViewById(R.id.spnCitaMA);
        spnCitaMA.setOnItemSelectedListener(this);

        AdminList.add("LISTADO");
        AdminList.add("CREAR");
        AdminAdapter = new ArrayAdapter<>(MenuAdministrador.this,android.R.layout.simple_spinner_item,AdminList);
        AdminAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAdminMA.setAdapter(AdminAdapter);
        spnAdminMA.setOnItemSelectedListener(this);

        PacienteList.add("LISTADO");
        PacienteList.add("CREAR");
        PacienteAdapter = new ArrayAdapter<>(MenuAdministrador.this,android.R.layout.simple_spinner_item,PacienteList);
        PacienteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPacienteMA.setAdapter(PacienteAdapter);
        spnPacienteMA.setOnItemSelectedListener(this);

        MedicoList.add("LISTADO");
        MedicoList.add("CREAR");
        MedicoAdapter = new ArrayAdapter<>(MenuAdministrador.this,android.R.layout.simple_spinner_item,MedicoList);
        MedicoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMedicoMA.setAdapter(MedicoAdapter);
        spnMedicoMA.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId()==R.id.spnAdminMA) {

        }
        else if(adapterView.getId()==R.id.spnPacienteMA) {

        }
        else if(adapterView.getId()==R.id.spnMedicoMA) {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}