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

public class AdapterAdmin extends ArrayAdapter<Administrador> {
    Context context;
    List<Administrador> arrayAdministradores;
    public AdapterAdmin(@NonNull Context context, List<Administrador>arrayAdministradores) {
        super(context, R.layout.list_administrador,arrayAdministradores);
        this.context = context;
        this.arrayAdministradores = arrayAdministradores;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_administrador,null,true);
        TextView tvid = view.findViewById(R.id.txtidA);
        TextView tvnom= view.findViewById(R.id.txtnomA);
        tvid.setText(arrayAdministradores.get(position).getIdAd());
        tvnom.setText(arrayAdministradores.get(position).getNomAd());
        return view;
    }
}
