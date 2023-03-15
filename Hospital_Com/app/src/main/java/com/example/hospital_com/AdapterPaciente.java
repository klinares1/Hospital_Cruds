package com.example.hospital_com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterPaciente extends ArrayAdapter<Paciente> {
    Context context;
    List<Paciente> arrayPacientes;
    public AdapterPaciente(@NonNull Context context, List<Paciente> arrayPacientes) {
        super(context, R.layout.list_administrador,arrayPacientes);
        this.context = context;
        this.arrayPacientes = arrayPacientes;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_paciente,null,true);
        TextView tvid = view.findViewById(R.id.txtidP);
        TextView tvnom= view.findViewById(R.id.txtnomP);
        tvid.setText(arrayPacientes.get(position).getIdPa());
        tvnom.setText(arrayPacientes.get(position).getNomPa());
        return view;
    }
}
