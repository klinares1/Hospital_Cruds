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

public class EditarPaciente extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txtNombreEP, txtApellidoEp, txtNumeroIdEP, txtFechaNac;
    Spinner spRhEP, spGeneroEP;
    Button btnConfirmarEP;
    ArrayList<String> GeneroList = new ArrayList<>();
    ArrayList<String> RHList = new ArrayList<>();
    ArrayAdapter<String> GeneroAdapter;
    ArrayAdapter<String> RHAdapter;
    RequestQueue requestQueue;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_paciente);

        txtNombreEP = findViewById(R.id.txtNombreEP);;
        txtApellidoEp = findViewById(R.id.txtApellidoEp);;
        txtNumeroIdEP = findViewById(R.id.txtNumeroIdEP);;
        txtFechaNac = findViewById(R.id.txtFechaNac);;
        spRhEP = findViewById(R.id.spRhEP);;
        spGeneroEP = findViewById(R.id.spGeneroEP);;
        btnConfirmarEP = findViewById(R.id.btnConfirmarEP);;

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
                        GeneroAdapter = new ArrayAdapter<>(EditarPaciente.this,
                                android.R.layout.simple_spinner_item,GeneroList);
                        GeneroAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spGeneroEP.setAdapter(GeneroAdapter);
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
        spGeneroEP.setOnItemSelectedListener(this);

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST,
                "http://10.28.0.232/hospital_cruds/Sangre_RH/ver_sangreRH.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("sangre_rh");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Nombre_RH = jsonObject.optString("Nombre_RH");
                        RHList.add(Nombre_RH);
                        RHAdapter = new ArrayAdapter<>(EditarPaciente.this,
                                android.R.layout.simple_spinner_item,RHList);
                        RHAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spRhEP.setAdapter(RHAdapter);
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
        spRhEP.setOnItemSelectedListener(this);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtFechaNac.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditarPaciente.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = year+"/"+month+"/"+day;
                        txtFechaNac.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        txtNombreEP.setText(CrudAdministrados.administradorArrayList.get(position).getNomAd());
        txtApellidoEp.setText(CrudAdministrados.administradorArrayList.get(position).getApeAd());
        txtNumeroIdEP.setText(CrudAdministrados.administradorArrayList.get(position).getIdAd());
        txtFechaNac.setText(CrudAdministrados.administradorArrayList.get(position).getPassAd());
    }

    public void actualizar(View view){
        final String id = txtNumeroIdEP.getText().toString().trim();
        final String nom = txtNombreEP.getText().toString().trim();
        final String ape = txtApellidoEp.getText().toString().trim();
        final String gen = spGeneroEP.getSelectedItem().toString().trim();
        final String fechan = txtFechaNac.getText().toString().trim();
        final String rh = spRhEP.getSelectedItem().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.28.0.232/hospital_cruds/Paciente/editarPaciente.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(EditarPaciente.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), CrudPacientes.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarPaciente.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("cod",id);
                params.put("nom",nom);
                params.put("ape",ape);
                params.put("em",gen);
                params.put("tel",fechan);
                params.put("fen",rh);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditarPaciente.this);
        requestQueue.add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}