package com.example.hospital_com;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class RecordarPass extends AppCompatActivity {

    EditText txtUsuarioRC;
    EditText txtNombreRC;
    TextView lblContraseñaRC;
    Button btnRecordarRC;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordar_pass);

        txtUsuarioRC = findViewById(R.id.txtUsuarioRC);
        txtNombreRC = findViewById(R.id.txtNombreRC);
        lblContraseñaRC = findViewById(R.id.lblContraseñaRC);
        btnRecordarRC = findViewById(R.id.btnRecordarRC);

        btnRecordarRC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                recuperarContraseña();
            }
        });
    }

    private void recuperarContraseña(){
        final String user = txtUsuarioRC.getText().toString().trim();
        final String nom = txtNombreRC.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(user.isEmpty()){
            Toast.makeText(this,"DIGITE EL USUARIO",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(nom.isEmpty()){
            Toast.makeText(this,"DIGITE SU NOMBRE",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    "http://10.28.0.230/hospital_cruds/recuperarContra.php", null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response.equals("Contraseña Administrador")) {
                        Toast.makeText(RecordarPass.this, "Contraseña Administrador", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("passwrd");
                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String contraseña = jsonObject.optString("Contraseña");
                                lblContraseñaRC.setText(contraseña);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(getApplicationContext(), RecordarPass.class));
                        finish();
                    }
                    else if (response.equals("Contraseña Paciente")) {
                        Toast.makeText(RecordarPass.this, "Contraseña Paciente", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("passwrd");
                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String contraseña = jsonObject.optString("Contraseña");
                                lblContraseñaRC.setText(contraseña);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(getApplicationContext(), RecordarPass.class));
                        finish();
                    }
                    else if (response.equals("Contraseña Medico")) {
                        Toast.makeText(RecordarPass.this, "Contraseña Medico", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("passwrd");
                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String contraseña = jsonObject.optString("Contraseña");
                                lblContraseñaRC.setText(contraseña);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(getApplicationContext(), RecordarPass.class));
                        finish();
                    }
                    else {
                        Toast.makeText(RecordarPass.this, "b "+response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(jsonObjectRequest);
        }
    }

}