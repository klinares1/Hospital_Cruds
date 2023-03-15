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

public class RegistrarMedico extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edttextNumIdRM, edttextnomRM, edttextApellidosRM, edttexPasswordRM;
    Spinner spEspecialidadRM, spGeneroRM;
    Button btnRegistrarRM;
    ArrayList<String> GeneroList = new ArrayList<>();
    ArrayList<String> EspeciList = new ArrayList<>();
    ArrayAdapter<String> GeneroAdapter;
    ArrayAdapter<String> EspeciAdapter;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_medico);

        edttextNumIdRM = findViewById(R.id.edttextNumIdRM);
        edttextnomRM = findViewById(R.id.edttextnomRM);
        edttextApellidosRM = findViewById(R.id.edttextApellidosRM);
        edttexPasswordRM = findViewById(R.id.edttexPasswordRM);
        spEspecialidadRM = findViewById(R.id.spEspecialidadRM);
        spGeneroRM = findViewById(R.id.spGeneroRM);
        btnRegistrarRM = findViewById(R.id.btnRegistrarRM);

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
                        GeneroAdapter = new ArrayAdapter<>(RegistrarMedico.this,
                                android.R.layout.simple_spinner_item,GeneroList);
                        GeneroAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spGeneroRM.setAdapter(GeneroAdapter);
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
        spGeneroRM.setOnItemSelectedListener(this);

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST,
                "http://10.28.0.232/hospital_cruds/Especialidad/ver_especialidad.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("especialidad");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Nombre_Especialidad = jsonObject.optString("Nombre_Especialidad");
                        EspeciList.add(Nombre_Especialidad);
                        EspeciAdapter = new ArrayAdapter<>(RegistrarMedico.this,
                                android.R.layout.simple_spinner_item,EspeciList);
                        EspeciAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spGeneroRM.setAdapter(EspeciAdapter);
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
        spEspecialidadRM.setOnItemSelectedListener(this);

        btnRegistrarRM.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                agregar();
            }
        });
    }

    private void agregar(){
        final String id = edttextNumIdRM.getText().toString().trim();
        final String nom = edttextnomRM.getText().toString().trim();
        final String ape = edttextApellidosRM.getText().toString().trim();
        final String pass = edttexPasswordRM.getText().toString().trim();
        final String esp = spEspecialidadRM.getSelectedItem().toString().trim();
        final String gen = spGeneroRM.getSelectedItem().toString().trim();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(id.isEmpty()){
            Toast.makeText(this,"DIGITE LA CEDULA",Toast.LENGTH_SHORT).show();
            return;
        }else if(nom.isEmpty()){
            Toast.makeText(this,"DIGITE EL NOMBRE",Toast.LENGTH_SHORT).show();
            return;
        } else if(ape.isEmpty()){
            Toast.makeText(this,"DIGITE EL APELLIDO",Toast.LENGTH_SHORT).show();
            return;
        }else if(pass.isEmpty()){
            Toast.makeText(this,"DIGITE LA CONTRASEÑA",Toast.LENGTH_SHORT).show();
            return;
        }else {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.28.0.232/hospital_cruds/Administrador/agregarAdmin.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("Administrador Registrado")) {
                                Toast.makeText(RegistrarMedico.this, "Administrador Registrado", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), RegistrarMedico.class));
                                finish();
                            } else {
                                Toast.makeText(RegistrarMedico.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistrarMedico.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();

                    params.put("id", id);
                    params.put("nom", nom);
                    params.put("ape", ape);
                    params.put("gen", gen);
                    params.put("esp", esp);
                    params.put("pass", pass);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(RegistrarMedico.this);
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