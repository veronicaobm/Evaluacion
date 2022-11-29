package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.contacto.Contactos;
import com.example.agenda.contacto.DatabaseHelper;
import com.example.agenda.contacto.DatePickerFragment;

public class NuevoContacto extends AppCompatActivity {
    private EditText nombre, telefono, direccion, correo;
    String fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        DatabaseHelper helper = new DatabaseHelper(this);

        EditText cumpleanos = (EditText) findViewById(R.id.cumple_nuevo);
        cumpleanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.cumple_nuevo){
                    mostrarCalendario();
                }
            }
        });

        nombre = ((EditText)findViewById(R.id.nombre_nuevo));
        telefono = ((EditText)findViewById(R.id.telefono_nuevo));
        direccion = ((EditText)findViewById(R.id.direccion_nuevo));
        correo = ((EditText)findViewById(R.id.correo_nuevo));

        Button botonAgregar = (Button) findViewById(R.id.btnAgregarContacto);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre_1 = nombre.getText().toString();
                String telefonoStr_1 = telefono.getText().toString();
                String direccion_1 = direccion.getText().toString();
                String correo_1 = correo.getText().toString();

                int telefono = 0;
                telefono = Integer.parseInt(telefonoStr_1);

                if(telefonoStr_1.length() != 9){
                    Toast.makeText(NuevoContacto.this, "Ingrese numero de 9 digitos", Toast.LENGTH_SHORT).show();
                }

                else if(nombre_1.length() == 0 || direccion_1.length() == 0 || correo_1.length() == 0){
                    Toast.makeText(NuevoContacto.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(NuevoContacto.this, nombre_1 + " agregado a la agenda", Toast.LENGTH_SHORT).show();
                    Contactos contactos;
                    contactos = new Contactos(nombre_1, telefono, direccion_1, correo_1);
                    helper.ingresarContacto(contactos);
                    Intent intent = new Intent(NuevoContacto.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        Button btnVolver = (Button) findViewById(R.id.btnVolverNuevo);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoContacto.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void mostrarCalendario() {
        DatePickerFragment nuevoFragment = DatePickerFragment.instancia(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                final String fecha = i2 + "/" + (i1+1) + "/" + i;
                EditText cumpleanos = (EditText) findViewById(R.id.cumple_nuevo);
                cumpleanos.setText(fecha);
            }
        });
        nuevoFragment.show(getSupportFragmentManager(), "datepicker");
    }

}
