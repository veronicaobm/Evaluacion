package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.contacto.Contactos;
import com.example.agenda.contacto.ListaContactos;

public class NuevoContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        Button btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresarContacto(view);
            }
        });
    }

    public void ingresarContacto(View view){
        String nombre = ((TextView)findViewById(R.id.nombre)).getText().toString();
        String telefonoStr = ((TextView)findViewById(R.id.telefono)).getText().toString();
        String direccion = ((TextView)findViewById(R.id.direccion)).getText().toString();
        String correo = ((TextView)findViewById(R.id.correo)).getText().toString();

        int telefono = 0;
        telefono = Integer.parseInt(telefonoStr);

        if(telefonoStr.length() != 9){
            Toast.makeText(this, "Ingrese numero de 9 digitos", Toast.LENGTH_SHORT).show();
        }

        else if(nombre.length() == 0 || direccion.length() == 0 || correo.length() == 0){
            Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(this, nombre + " agregado a la agenda", Toast.LENGTH_SHORT).show();
            Contactos contactos;
            contactos = new Contactos(nombre, telefono, direccion, correo);
            ListaContactos.getInstancia().agregarContacto(contactos);
            finish();
        }
    }
}