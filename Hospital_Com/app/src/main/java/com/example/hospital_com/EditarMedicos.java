package com.example.hospital_com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.HashMap;
import java.util.Map;

public class EditarMedicos extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edttextNomEM2, edttextApeEM2, edttextNumIdEM2,edttexPasswordEM;
    Spinner spEspecialidadEM2, spGeneroEM2;
    ArrayList<String> GeneroList = new ArrayList<>();
    ArrayList<String> EspeciList = new ArrayList<>();
    ArrayAdapter<String> GeneroAdapter;
    ArrayAdapter<String> EspeciAdapter;
    RequestQueue requestQueue;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_medicos);

        edttextNomEM2 = findViewById(R.id.edttextNomEM2);
        edttextApeEM2 = findViewById(R.id.edttextApeEM2);
        edttextNumIdEM2 = findViewById(R.id.edttextNumIdEM2);
        spEspecialidadEM2 = findViewById(R.id.spEspecialidadEM2);
        spGeneroEM2 = findViewById(R.id.spGeneroEM2);
        edttexPasswordEM = findViewById(R.id.edttexPasswordEM);

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
                        GeneroAdapter = new ArrayAdapter<>(EditarMedicos.this,
                                android.R.layout.simple_spinner_item,GeneroList);
                        GeneroAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spGeneroEM2.setAdapter(GeneroAdapter);
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
        spGeneroEM2.setOnItemSelectedListener(this);

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
                        EspeciAdapter = new ArrayAdapter<>(EditarMedicos.this,
                                android.R.layout.simple_spinner_item,EspeciList);
                        EspeciAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spGeneroEM2.setAdapter(EspeciAdapter);
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
        spEspecialidadEM2.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        edttextNumIdEM2.setText(CrudMedicos.medicoArrayList.get(position).getIdMed());
        edttextNomEM2.setText(CrudMedicos.medicoArrayList.get(position).getNomMed());
        edttextApeEM2.setText(CrudMedicos.medicoArrayList.get(position).getApeMed());
        edttexPasswordEM.setText(CrudMedicos.medicoArrayList.get(position).getPassMed());
    }

    public void actualizar(View view){
        final String id = edttextNumIdEM2.getText().toString().trim();
        final String nom = edttextNomEM2.getText().toString().trim();
        final String ape = edttextApeEM2.getText().toString().trim();
        final String gen = spGeneroEM2.getSelectedItem().toString().trim();
        final String esp = spEspecialidadEM2.getSelectedItem().toString().trim();
        final String pass = edttexPasswordEM.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.28.0.232/hospital_cruds/Medico/editarMedico.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(EditarMedicos.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), CrudMedicos.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarMedicos.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("cod",id);
                params.put("nom",nom);
                params.put("ape",ape);
                params.put("gen",gen);
                params.put("esp",esp);
                params.put("pass",pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditarMedicos.this);
        requestQueue.add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}