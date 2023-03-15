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

public class AdapterMedico extends ArrayAdapter<Medico> {
    Context context;
    List<Medico> arrayMedico;
    public AdapterMedico(@NonNull Context context, List<Medico>arrayMedico) {
        super(context, R.layout.list_administrador,arrayMedico);
        this.context = context;
        this.arrayMedico = arrayMedico;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_medico,null,true);
        TextView tvid = view.findViewById(R.id.txtidM);
        TextView tvnom= view.findViewById(R.id.txtnomM);
        tvid.setText(arrayMedico.get(position).getIdMed());
        tvnom.setText(arrayMedico.get(position).getNomMed());
        return view;
    }
}
