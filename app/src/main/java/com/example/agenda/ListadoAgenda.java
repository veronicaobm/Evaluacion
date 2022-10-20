package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.agenda.contacto.Contactos;
import com.example.agenda.contacto.ListaContactos;

import java.util.ArrayList;

public class ListadoAgenda extends ListActivity {
    ListView agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarAgenda();
    }

    public void cargarAgenda(){
        agenda = getListView();
        ArrayList<Contactos> contactos = ListaContactos.getInstancia().getListaContactos();
        ArrayAdapter<Contactos> listaAdapter = new ArrayAdapter<Contactos>(this, android.R.layout.simple_list_item_1, contactos);
        agenda.setAdapter(listaAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int posicion, long id){
        Intent intent = new Intent(ListadoAgenda.this, Detalles.class);
        intent.putExtra("idContactos", (int)id);
        startActivityForResult(intent, 1);
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