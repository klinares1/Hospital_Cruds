package com.example.hospital_com;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegistrarPaciente extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText edttextNumIdRP, edttextNomRP, edttextApelRP, edttextFechaNacRP, edttextPasswordRP;
    Spinner spGeneroRP, spRhRp;
    Button btnRegistroRP;
    ArrayList<String> GeneroList = new ArrayList<>();
    ArrayList<String> RHList = new ArrayList<>();
    ArrayAdapter<String> GeneroAdapter;
    ArrayAdapter<String> RHAdapter;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);

        edttextNumIdRP = findViewById(R.id.edttextNumIdRP);
        edttextNomRP = findViewById(R.id.edttextNomRP);
        edttextApelRP = findViewById(R.id.edttextApelRP);
        edttextFechaNacRP = findViewById(R.id.edttextFechaNacRP);
        edttextPasswordRP = findViewById(R.id.edttextPasswordRP);
        spGeneroRP = findViewById(R.id.spGeneroRP);
        spRhRp = findViewById(R.id.spRhRp);
        btnRegistroRP = findViewById(R.id.btnRegistroRP);

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                "http://10.28.0.232/hospital_cruds/Genero/ver_genero.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("genero");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Nombre_Genero = jsonObject.optString("Nombre_Genero");
                        GeneroList.add(Nombre_Genero);
                        GeneroAdapter = new ArrayAdapter<>(RegistrarPaciente.this,
                                android.R.layout.simple_spinner_item,GeneroList);
                        GeneroAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spGeneroRP.setAdapter(GeneroAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        spGeneroRP.setOnItemSelectedListener(this);

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST,
                "http://10.28.0.232/hospital_cruds/SangreRH/ver_sangreRH.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("sangre_rh");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Nombre_RH = jsonObject.optString("Nombre_RH");
                        RHList.add(Nombre_RH);
                        RHAdapter = new ArrayAdapter<>(RegistrarPaciente.this,
                                android.R.layout.simple_spinner_item,RHList);
                        RHAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spRhRp.setAdapter(RHAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest1);
        spRhRp.setOnItemSelectedListener(this);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edttextFechaNacRP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrarPaciente.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = year+"/"+month+"/"+day;
                        edttextFechaNacRP.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        btnRegistroRP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                agregar();
            }
        });

    }

    private void agregar() {
        final String id = edttextNumIdRP.getText().toString().trim();
        final String nom = edttextNomRP.getText().toString().trim();
        final String ape = edttextApelRP.getText().toString().trim();
        final String gen = spGeneroRP.getSelectedItem().toString().trim();
        final String fechan = edttextFechaNacRP.getText().toString().trim();
        final String rh = spRhRp.getSelectedItem().toString().trim();
        final String pass = edttextPasswordRP.getText().toString().trim();

        String id_Gen="";
        String id_rh="";

        switch (gen){
            case "Femenino":{
                id_Gen = "1";
                break;
            }
            case "Masculino":{
                id_Gen = "2";
                break;
            }
            case "Otro":{
                id_Gen = "3";
                break;
            }
        }
        switch (rh){
            case "A+":{
                id_rh = "1";
                break;
            }
            case "A-":{
                id_rh = "2";
                break;
            }
            case "B+":{
                id_rh = "3";
                break;
            }
            case "B-":{
                id_rh = "4";
                break;
            }case "O+":{
                id_rh = "5";
                break;
            }
            case "O-":{
                id_rh = "6";
                break;
            }case "AB+":{
                id_rh = "7";
                break;
            }
            case "AB-":{
                id_rh = "8";
                break;
            }
        }
        final String id_Genf=id_Gen;
        final String id_rhf=id_rh;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if (id.isEmpty()) {
            Toast.makeText(this, "DIGITE LA CEDULA", Toast.LENGTH_SHORT).show();
            return;
        } else if (nom.isEmpty()) {
            Toast.makeText(this, "DIGITE EL NOMBRE", Toast.LENGTH_SHORT).show();
            return;
        } else if (ape.isEmpty()) {
            Toast.makeText(this, "DIGITE EL APELLIDO", Toast.LENGTH_SHORT).show();
            return;
        } else if (fechan.isEmpty()) {
            Toast.makeText(this, "DIGITE LA FECHA", Toast.LENGTH_SHORT).show();
            return;
        } else if (pass.isEmpty()) {
            Toast.makeText(this, "DIGITE LA CONTRASEÑA", Toast.LENGTH_SHORT).show();
            return;
        } else {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.28.0.232/hospital_cruds/Paciente/agregarPaciente.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("Paciente Registrado")) {
                                Toast.makeText(RegistrarPaciente.this, "Paciente Registrado", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), RegistrarPaciente.class));
                                finish();
                            } else {
                                Toast.makeText(RegistrarPaciente.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistrarPaciente.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", id);
                    params.put("nom", nom);
                    params.put("ape", ape);
                    params.put("gen", id_Genf);
                    params.put("fechan", fechan);
                    params.put("rh", id_rhf);
                    params.put("pass", pass);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(RegistrarPaciente.this);
            requestQueue.add(stringRequest);
        }//fin del else
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}