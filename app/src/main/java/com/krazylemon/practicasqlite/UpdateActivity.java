package com.krazylemon.practicasqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {

    EditText nombre_txt,celular_txt,email_txt;
    Button edit_button;

    String id,nombre,celular,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        nombre_txt = findViewById(R.id.nombre_txt);
        celular_txt = findViewById(R.id.celular_txt);
        email_txt = findViewById(R.id.email_txt);
        edit_button = findViewById(R.id.edit_button);

        getAndSetIntentData();

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper db = new dbHelper(UpdateActivity.this);
                nombre = nombre_txt.getText().toString().trim();
                celular = celular_txt.getText().toString().trim();
                email = email_txt.getText().toString().trim();
                db.updateContacto(id,nombre,celular,email);
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nombre") && getIntent().hasExtra("celular") && getIntent().hasExtra("email")){

            id = getIntent().getStringExtra("id");
            nombre = getIntent().getStringExtra("nombre");
            celular = getIntent().getStringExtra("celular");
            email = getIntent().getStringExtra("email");

            nombre_txt.setText(nombre);
            celular_txt.setText(celular);
            email_txt.setText(email);

        }else {
            Toast.makeText(this,"No data Received",Toast.LENGTH_SHORT).show();
        }
    }
}