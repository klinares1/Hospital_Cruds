package com.example.hospital_com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edttextUsuarioLI4, edttextPassLI4;
    Button btnIngresarLI4;
    TextView lblOlvidarPassLI4, lblRegisPacLI4, lblRegisMedLI4, lblRegisAdminLI4;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edttextUsuarioLI4 = findViewById(R.id.edttextUsuarioLI);
        edttextPassLI4 = findViewById(R.id.edttextPassLI);
        btnIngresarLI4 = findViewById(R.id.btnIngresarLI);
        lblOlvidarPassLI4 = findViewById(R.id.lblOlvidarPassLI);
        lblRegisPacLI4 = findViewById(R.id.lblRegisPacLI);
        lblRegisMedLI4 = findViewById(R.id.lblRegisMedLI);
        lblRegisAdminLI4 = findViewById(R.id.lblRegisAdminLI);

        btnIngresarLI4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                validarUsuario("http://192.168.0.12/hospital_cruds/validarusuario.php");
            }
        });

        lblOlvidarPassLI4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

              intent = new Intent(getApplicationContext(), CrudMedicos.class);
                startActivity(intent);
            }
        });

        lblRegisPacLI4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), RegistrarPaciente.class);
                startActivity(intent);
            }
        });

        lblRegisMedLI4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), RegistrarMedico.class);
                startActivity(intent);
            }
        });

        lblRegisAdminLI4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), RegistrarAdministrador.class);
                startActivity(intent);
            }
        });
    }

    private void validar(){
        final String user = edttextUsuarioLI4.getText().toString().trim();
        final String pass = edttextPassLI4.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(user.isEmpty()){
            Toast.makeText(this,"DIGITE EL USUARIO",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(pass.isEmpty()){
            Toast.makeText(this,"DIGITE LA CONTRASEÑA",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            StringRequest request = new StringRequest(Request.Method.POST, "http://10.28.0.232/hospital_cruds/join.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Bienvenido_Administrador")) {
                                Toast.makeText(MainActivity.this, "Bienvenido Administrador", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), MenuAdministrador.class));
                                finish();
                            }
                            else if (response.equals("Bienvenido_Paciete")) {
                                Toast.makeText(MainActivity.this, "Bienvenido Paciente", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), MenuPaciente.class));
                                finish();
                            }
                            else if (response.equals("Bienvenido_Medico")) {
                                Toast.makeText(MainActivity.this, "Bienvenido Medico", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), MenuMedico.class));
                                finish();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "b "+response, Toast.LENGTH_SHORT).show();
                                //edttextUsuarioLI4.setText(response);
                                progressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "a "+error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String,String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("user",user);
                    params.put("pass",pass);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
    }
    private void validarUsuario(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().contains("Nombre")){
                    String cedula_usuario = edttextUsuarioLI4.getText().toString();
                    Intent intent;
                    if(cedula_usuario.startsWith("1")){
                        intent=new Intent(getApplicationContext(), CrudAdministrados.class);
                        startActivity(intent);
                    }
                    if(cedula_usuario.startsWith("2")){
                        intent=new Intent(getApplicationContext(), CrudPacientes.class);
                        startActivity(intent);
                    }
                    if(cedula_usuario.startsWith("3")){
                        intent=new Intent(getApplicationContext(), CrudMedicos.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("CC_Administrador", edttextUsuarioLI4.getText().toString());
                parametros.put("Contraseña_Admin", edttextPassLI4.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}