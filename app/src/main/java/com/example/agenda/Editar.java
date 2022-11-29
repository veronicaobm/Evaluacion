package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.contacto.Contactos;
import com.example.agenda.contacto.DatabaseHelper;
import com.example.agenda.contacto.ListaContactos;

public class Editar extends AppCompatActivity {
    public Contactos contactos;
    public Intent intent;
    private EditText nombre, telefono, direccion, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        DatabaseHelper helper = new DatabaseHelper(this);

        nombre = ((EditText)findViewById(R.id.nombre_editar));
        telefono = ((EditText)findViewById(R.id.telefono_editar));
        direccion = ((EditText)findViewById(R.id.direccion_editar));
        correo = ((EditText)findViewById(R.id.correo_editar));


        Button botonEditar = (Button) findViewById(R.id.btnEditarOk);
        botonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_editar = nombre.getText().toString();
                String telefono_editar = telefono.getText().toString();
                String direccion_editar = direccion.getText().toString();
                String correo_editar = correo.getText().toString();
                if(!nombre_editar.isEmpty() && !telefono_editar.isEmpty() && !direccion_editar.isEmpty() && !correo_editar.isEmpty() ){
                    int telefono_edit = 0;
                    telefono_edit = Integer.parseInt(telefono_editar);

                    helper.EditarContacto(nombre_editar, telefono_edit, direccion_editar, correo_editar);
                    Toast.makeText(Editar.this, nombre_editar + " editado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Editar.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Editar.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button botonEliminar = (Button) findViewById(R.id.btnEliminarEditar);
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_eliminar = nombre.getText().toString();
                if(!nombre_eliminar.isEmpty()){
                    helper.eliminarContacto(nombre_eliminar);
                    Toast.makeText(Editar.this, nombre_eliminar + " eliminado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Editar.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Editar.this, "Ingrese nombre", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button btnVolver = (Button) findViewById(R.id.btnVolverEditar);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Editar.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}