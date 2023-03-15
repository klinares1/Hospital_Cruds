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

public class CrudMedicos extends AppCompatActivity {

    ListView listMostrarCM;
    AdapterMedico adapter;
    public static ArrayList<Medico> medicoArrayList = new ArrayList<>();
    String url ="http://10.28.0.232/hospital_cruds/Medico/mostrarMedico.php";
    Medico medico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_medicos);

        listMostrarCM = findViewById(R.id.listMostrarCM);
        adapter = new AdapterMedico(this, medicoArrayList);
        listMostrarCM.setAdapter((ListAdapter) adapter);
        listMostrarCM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogItem = {"Ver","Editar","Eliminar"};
                builder.setTitle(medicoArrayList.get(position).getNomMed());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),DetallesMedico.class).putExtra("position",position));
                                break;

                            case 1:
                                startActivity(new Intent(getApplicationContext(),EditarMedicos.class).putExtra("position",position));
                                break;
                            case 2:
                                elimMedico(medicoArrayList.get(position).getIdMed());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        verMedico();
    }

    public void elimMedico(final String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.28.0.232/hospital_cruds/Medico/eliminarMedico.php"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Datos eliminados")) {
                    Toast.makeText(CrudMedicos.this, "No se pudo Eliminar el Medico", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), CrudMedicos.class));
                } else {
                    Toast.makeText(CrudMedicos.this, "Medico Eliminado", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CrudMedicos.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
    public void verMedico(){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        medicoArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("succes");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");
                            if(succes.equals("1")){
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("CC_Medico");
                                    String nom = object.getString("Nombre_Medico");
                                    String ape = object.getString("Apellido_Medico");
                                    String gen = object.getString("Id_Genero");
                                    String esp = object.getString("Id_Especialidad");
                                    String pass = object.getString("Contraseña");

                                    medico = new Medico(id,nom,ape,gen,esp,pass);
                                    medicoArrayList.add(medico);
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
                Toast.makeText(CrudMedicos.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}