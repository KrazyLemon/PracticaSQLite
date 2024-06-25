package com.krazylemon.practicasqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    EditText nombre_input,email_input,celular_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nombre_input = findViewById(R.id.nombre_input);
        email_input = findViewById(R.id.email_input);
        celular_input = findViewById(R.id.celular_input);

        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper db = new dbHelper(AddActivity.this);
                db.addContacto(nombre_input.getText().toString().trim(),
                        celular_input.getText().toString().trim(),
                        email_input.getText().toString().trim()
                );


            }
        });

    }
}