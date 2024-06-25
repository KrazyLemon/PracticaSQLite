package com.krazylemon.practicasqlite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList contacto_id, contacto_nombre, contacto_celular, contacto_email;

    private int position;

    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList contacto_id,
                  ArrayList contacto_nombre,
                  ArrayList contacto_celular,
                  ArrayList contacto_email){
        this.activity = activity;
        this.context = context;
        this.contacto_id = contacto_id;
        this.contacto_nombre = contacto_nombre;
        this.contacto_celular = contacto_celular;
        this.contacto_email = contacto_email;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_contact_txt.setText(String.valueOf(contacto_id.get(position)));
        holder.nombre_contact_txt.setText(String.valueOf(contacto_nombre.get(position)));
        holder.celular_contact_txt.setText(String.valueOf(contacto_celular.get(position)));
        holder.correo_contact_txt.setText(String.valueOf(contacto_email.get(position)));

        holder.edit_btn.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(contacto_id.get(position)));
                intent.putExtra("nombre",String.valueOf(contacto_nombre.get(position)));
                intent.putExtra("celular",String.valueOf(contacto_celular.get(position)));
                intent.putExtra("email",String.valueOf(contacto_email.get(position)));
                activity.startActivityForResult(intent,1);
            }
        } );

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setMessage("ELiminar Registro?")
                        .setCancelable(false)
                        .setPositiveButton("si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper db = new dbHelper(context);
                                db.deleteContacto(String.valueOf(contacto_id.get(position)));
                                Intent intent = new Intent(context, MainActivity.class);
                                activity.startActivity(intent);
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Eliminar Contacto");
                titulo.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacto_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_contact_txt,nombre_contact_txt,correo_contact_txt,celular_contact_txt;
        ImageButton edit_btn, delete_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_contact_txt = itemView.findViewById(R.id.id_contact_txt);
            nombre_contact_txt = itemView.findViewById(R.id.nombre_contact_txt);
            correo_contact_txt = itemView.findViewById(R.id.correo_contact_txt);
            celular_contact_txt = itemView.findViewById(R.id.celular_contact_txt);



            edit_btn = itemView.findViewById(R.id.edit_btn);
            delete_btn = itemView.findViewById(R.id.delete_btn);
        }
    }
}
