package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.contacto.Contactos;
import com.example.agenda.contacto.ListaContactos;

import java.io.Serializable;
import java.util.ArrayList;

public class Editar extends AppCompatActivity {
    public Contactos contactos;
    public Intent intent;
    String nombre_nuevo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        intent = getIntent();
        int id = (Integer) intent.getExtras().get("idContactos");

        contactos = ListaContactos.getInstancia().getContactos(id);

        TextView txtDatos = (TextView) findViewById(R.id.tvDatos);
        String datos = "Contacto a actualizar\n\nNombre: " + contactos.getNombre()
                + "\nTelefono: " + contactos.getTelefono()
                + "\nDireccion: " + contactos.getDireccion()
                + "\nCorreo: "+ contactos.getCorreo();
        txtDatos.setText(datos);

        String nombre_eliminar = (contactos.getNombre()).toString();

        Button guardar = (Button) findViewById(R.id.btnGuardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre_nuevo = ((TextView)findViewById(R.id.EditarNombre)).getText().toString();
                if(nombre_nuevo.length() == 0){
                    Toast.makeText(getApplicationContext(), "Nombre vacio", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Editar.this, MainActivity.class);
                    startActivity(intent);
                }
                contactos.setNombre(nombre_nuevo);

                String telefono_nuevoStr = ((TextView)findViewById(R.id.EditarTelefono)).getText().toString();
                int telefono_nuevoInt = Integer.parseInt(telefono_nuevoStr);

                if(telefono_nuevoStr.length() != 9){
                    Toast.makeText(getApplicationContext(), "Ingrese numero de 9 dígitos", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Editar.this, MainActivity.class);
                    startActivity(intent);
                }

                contactos.setTelefono(telefono_nuevoInt);

                String direccion_nuevo = ((TextView)findViewById(R.id.EditarDireccion)).getText().toString();
                if(direccion_nuevo.length() == 0){
                    Toast.makeText(getApplicationContext(), "Direccion vacía", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Editar.this, MainActivity.class);
                    startActivity(intent);
                }
                contactos.setDireccion(direccion_nuevo);

                String correo_nuevo = ((TextView)findViewById(R.id.EditarCorreo)).getText().toString();
                if(correo_nuevo.length() == 0){
                    Toast.makeText(getApplicationContext(), "Correo vacío", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Editar.this, MainActivity.class);
                    startActivity(intent);
                }
                contactos.setCorreo(correo_nuevo);

                String datosNuevos = "Datos actualizados\n\nNombre: " + contactos.getNombre()
                        + "\nTelefono: " + contactos.getTelefono()
                        + "\nDireccion: " + contactos.getDireccion()
                        + "\nCorreo: "+ contactos.getCorreo();
                txtDatos.setText(datosNuevos);

            }
        });

        Button eliminar = (Button) findViewById(R.id.btnEliminarContacto);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Contactos> ctc = ListaContactos.getInstancia().getListaContactos();
                for(int i=0; i <  ctc.size(); i++){
                    if((ctc.get(i)).toString().equals(nombre_eliminar)){
                        ctc.remove(i);
                        Toast.makeText(getApplicationContext(), nombre_eliminar +" eliminado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Editar.this, MainActivity.class);
                        startActivity(intent);
                    }
                    if((ctc.get(i)).toString().equals(nombre_nuevo)){
                        ctc.remove(i);
                        Toast.makeText(getApplicationContext(), nombre_nuevo +" eliminado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Editar.this, MainActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });

        Button volver = (Button) findViewById(R.id.btnVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Editar.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}