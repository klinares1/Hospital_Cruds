package com.example.hospital_com;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CrudAdministrados extends AppCompatActivity {

    ListView listMostrarCPA;
    AdapterAdmin adapter;
    public static ArrayList<Administrador> administradorArrayList = new ArrayList<>();
    String url ="http://10.28.0.232/hospital_cruds/Administrador/mostrarAdmin.php";
    Administrador administrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_administrados);

        listMostrarCPA = findViewById(R.id.listMostrarCA);
        adapter = new AdapterAdmin(this, administradorArrayList);
        listMostrarCPA.setAdapter((ListAdapter) adapter);
        listMostrarCPA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogItem = {"Ver","Editar","Eliminar"};
                builder.setTitle(administradorArrayList.get(position).getNomAd());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),DetallesAdministrador.class).putExtra("position",position));
                                break;

                            case 1:
                                startActivity(new Intent(getApplicationContext(),EditarAdministrador.class).putExtra("position",position));
                                break;
                            case 2:
                                elimAdmin(administradorArrayList.get(position).getIdAd());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        verAdministrador();
    }

    public void elimAdmin(final String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.28.0.232/hospital_cruds/Administrador/eliminarAdmin.php"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Datos eliminados")) {
                    Toast.makeText(CrudAdministrados.this, "No se pudo Eliminar el Administrador", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), CrudAdministrados.class));
                } else {
                    Toast.makeText(CrudAdministrados.this, "Administrador Eliminado", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CrudAdministrados.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("id",id);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void verAdministrador(){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        administradorArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("succes");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");

                            if(succes.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("CC_Administrador");
                                    String nom = object.getString("Nombre_Admin");
                                    String ape = object.getString("Apellido_Admin");
                                    String pass = object.getString("Contraseña_Admin");

                                    administrador = new Administrador(id,nom,ape,pass);
                                    administradorArrayList.add(administrador);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CrudAdministrados.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}