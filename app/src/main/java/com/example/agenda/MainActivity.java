package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.agenda.contacto.Contactos;
import com.example.agenda.contacto.ListaContactos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button verContactos = (Button) findViewById(R.id.btnVerContactos);
        verContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Contactos> contactos = ListaContactos.getInstancia().getListaContactos();
                if(contactos.size() > 0){
                    Intent intent = new Intent(MainActivity.this, ListadoAgenda.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "No hay contactos ingresados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button botonAgregar = (Button) findViewById(R.id.btnAgregarContacto);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NuevoContacto.class);
                startActivity(intent);
            }
        });





    }
}