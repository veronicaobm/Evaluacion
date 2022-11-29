package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.contacto.Contactos;
import com.example.agenda.contacto.DatabaseHelper;
import com.example.agenda.contacto.DatabaseHelper;
import com.example.agenda.contacto.ListaContactos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText nombre, telefono, direccion, correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper helper = new DatabaseHelper(this);

        nombre = ((EditText)findViewById(R.id.nombre));

        Button verContactos = (Button) findViewById(R.id.btnVerContactos);
        verContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    ArrayList<Contactos> contactos = (ArrayList<Contactos>) helper.ListaContactos();
                    Intent intent = new Intent(MainActivity.this, ListadoAgenda.class);
                    startActivity(intent);
                }catch(Exception ex){
                    Toast.makeText(MainActivity.this, "No hay contactos ingresados", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button botonBuscar = (Button) findViewById(R.id.btnBuscarContacto);
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();
                String nombre_buscar = nombre.getText().toString();

                if(!nombre_buscar.isEmpty()){
                    Cursor cursor = db.rawQuery("SELECT TELEFONO, CORREO, DIRECCION FROM CONTACTOS WHERE NOMBRE= '"+nombre_buscar+"'", null);
                    if(cursor.moveToFirst()){

                        telefono.setText(cursor.getString(2));
                        direccion.setText(cursor.getString(3));
                        correo.setText(cursor.getString(4));
                        db.close();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "No existe en contactos", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Ingrese nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button botonEliminar = (Button) findViewById(R.id.btnEliminar);
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_eliminar = nombre.getText().toString();
                if(!nombre_eliminar.isEmpty()){
                    helper.eliminarContacto(nombre_eliminar);
                    Toast.makeText(MainActivity.this, nombre_eliminar + " eliminado", Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this, "Ingrese nombre", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button botonEditar = (Button) findViewById(R.id.btnEditarContacto);
        botonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Editar.class);
                startActivity(intent);
            }
        });

        Button botonUbicacion = (Button) findViewById(R.id.btnUbicacion);
        botonUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        Button btnAgregar = (Button) findViewById(R.id.btnAgregarContacto);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NuevoContacto.class);
                startActivity(intent);
            }
        });
    }
}