package com.example.hospital_com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistrarAdministrador extends AppCompatActivity {

    EditText edttextNumIdRA;
    EditText edttextNomRA;
    EditText edttextApellidosRA;
    EditText edttextPassRA;
    Button btnRegistroRA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_administrador);

        edttextNumIdRA = findViewById(R.id.edttextNumIdRA);
        edttextNomRA = findViewById(R.id.edttextNomRA);;
        edttextApellidosRA = findViewById(R.id.edttextApellidosRA);;
        edttextPassRA = findViewById(R.id.edttextPassRA);;
        btnRegistroRA = findViewById(R.id.btnRegistroRA);;

        btnRegistroRA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                agregar();
            }
        });
    }

    private void agregar(){
        final String id = edttextNumIdRA.getText().toString().trim();
        final String nom = edttextNomRA.getText().toString().trim();
        final String ape = edttextApellidosRA.getText().toString().trim();
        final String pass = edttextPassRA.getText().toString().trim();

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
                                Toast.makeText(RegistrarAdministrador.this, "Administrador Registrado", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), RegistrarAdministrador.class));
                                finish();
                            } else {
                                Toast.makeText(RegistrarAdministrador.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistrarAdministrador.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();

                    params.put("id", id);
                    params.put("nom", nom);
                    params.put("ape", ape);
                    params.put("pass", pass);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(RegistrarAdministrador.this);
            requestQueue.add(stringRequest);
        }//fin del else
    }

}