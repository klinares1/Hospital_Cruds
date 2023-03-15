package com.example.hospital_com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class EditarAdministrador extends AppCompatActivity {

    EditText edttextNomEA, edttextApellidoEA, edttextNumIdEA, edttextPassEA;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_administrador);

        edttextNomEA = findViewById(R.id.edttextNomEA);
        edttextApellidoEA = findViewById(R.id.edttextApellidoEA);
        edttextNumIdEA = findViewById(R.id.edttextNumIdEA);
        edttextPassEA = findViewById(R.id.edttextPassEA);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        edttextNumIdEA.setText(CrudAdministrados.administradorArrayList.get(position).getIdAd());;
        edttextNomEA.setText(CrudAdministrados.administradorArrayList.get(position).getNomAd());;
        edttextApellidoEA.setText(CrudAdministrados.administradorArrayList.get(position).getApeAd());;
        edttextPassEA.setText(CrudAdministrados.administradorArrayList.get(position).getPassAd());;
    }

    public void actualizar(View view){
        final String id = edttextNumIdEA.getText().toString().trim();
        final String nom = edttextNomEA.getText().toString().trim();
        final String ape = edttextApellidoEA.getText().toString().trim();
        final String pass = edttextPassEA.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.5/hospital_cruds/Administrador/editarAdmin.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(EditarAdministrador.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), CrudAdministrados.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarAdministrador.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("cod",id);
                params.put("nom",nom);
                params.put("ape",ape);
                params.put("em",pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditarAdministrador.this);
        requestQueue.add(request);
    }
}