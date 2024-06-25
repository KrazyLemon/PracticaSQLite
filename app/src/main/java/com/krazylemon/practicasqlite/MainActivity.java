package com.krazylemon.practicasqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    dbHelper db;
    ArrayList<String> Contacto_id, Contacto_Nombre, Contacto_Celular, Contacto_Email;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        db = new dbHelper(MainActivity.this);
        Contacto_id = new ArrayList<>();
        Contacto_Nombre = new ArrayList<>();
        Contacto_Celular = new ArrayList<>();
        Contacto_Email = new ArrayList<>();

        storeData();

        customAdapter = new CustomAdapter(MainActivity.this,this,Contacto_id,Contacto_Nombre,Contacto_Celular,Contacto_Email);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeData(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data to display",Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){
                Contacto_id.add(cursor.getString(0));
                Contacto_Nombre.add(cursor.getString(1));
                Contacto_Celular.add(cursor.getString(2));
                Contacto_Email.add(cursor.getString(3));
            }
        }
    }
}