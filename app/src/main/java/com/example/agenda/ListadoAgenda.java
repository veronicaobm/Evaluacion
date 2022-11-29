package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.agenda.contacto.Contactos;
import com.example.agenda.contacto.DatabaseHelper;
import com.example.agenda.contacto.ListaContactos;

import java.util.ArrayList;

public class ListadoAgenda extends ListActivity {
    ListView agenda;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarAgenda();
    }

    public void cargarAgenda(){
        agenda = getListView();
        ArrayList<Contactos> contactos = helper.ListaContactos();
        ArrayAdapter<Contactos> listaAdapter = new ArrayAdapter<Contactos>(
                this, android.R.layout.simple_list_item_1, contactos);
        agenda.setAdapter(listaAdapter);
        agenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = agenda.getItemAtPosition(i);
                String linea = obj.toString();
                String[] separar = linea.split(":");
                Intent intent = new Intent(ListadoAgenda.this, Detalles.class);
                intent.putExtra("nombreContacto", separar[0]);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                cargarAgenda();
            }
        }
    }
}