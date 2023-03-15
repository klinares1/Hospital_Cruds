package com.example.hospital_com;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class CrudPacientes extends AppCompatActivity {

    ListView listMostrarCPA;
    AdapterPaciente adapter;
    public static ArrayList<Paciente> pacienteArrayList = new ArrayList<>();
    String url ="http://10.28.0.232/hospital_cruds/Paciente/mostrarPaciente.php";
    Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_pacientes);

        listMostrarCPA = findViewById(R.id.listMostrarCPA);
        adapter = new AdapterPaciente(this, pacienteArrayList);
        listMostrarCPA.setAdapter((ListAdapter) adapter);
        listMostrarCPA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogItem = {"Ver","Editar","Eliminar"};
                builder.setTitle(pacienteArrayList.get(position).getNomPa());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),DetallesPacientes.class).putExtra("position",position));
                                break;

                            case 1:
                                startActivity(new Intent(getApplicationContext(),EditarPaciente.class).putExtra("position",position));
                                break;
                            case 2:
                                elimPaciente(pacienteArrayList.get(position).getIdPa());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        verPaciente();
    }

    public void elimPaciente(final String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.28.0.232/hospital_cruds/Paciente/eliminarPaciente.php"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Datos eliminados")) {
                    Toast.makeText(CrudPacientes.this, "No se pudo Eliminar el Paciente", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), CrudPacientes.class));
                } else {
                    Toast.makeText(CrudPacientes.this, "Paciente Eliminado", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CrudPacientes.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
    public void verPaciente(){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pacienteArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("succes");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");
                            if(succes.equals("1")){
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("CC_Paciente");
                                    String nom = object.getString("Nombre_Paciente");
                                    String ape = object.getString("Apellido_Paciente");
                                    String gen = object.getString("Id_Genero");
                                    String fechan = object.getString("Fecha_Nac");
                                    String rh = object.getString("Id_RH");
                                    String pass = object.getString("Contraseña");

                                    paciente = new Paciente(id,nom,ape,gen,fechan,rh,pass);
                                    pacienteArrayList.add(paciente);
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
                Toast.makeText(CrudPacientes.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}