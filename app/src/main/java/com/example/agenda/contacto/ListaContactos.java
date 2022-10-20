package com.example.agenda.contacto;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentNavigableMap;

public class ListaContactos {
    private static ListaContactos instancia = new ListaContactos();
    private ArrayList<Contactos> listadecontactos;

    private ListaContactos(){
        listadecontactos = new ArrayList<>();
    }

    public static ListaContactos getInstancia() {

        return instancia;
    }

    public void agregarContacto(Contactos contactos){

        listadecontactos.add(contactos);
    }

    public Contactos getContactos(int id){

        return listadecontactos.get(id);
    }

    public ArrayList<Contactos> getListaContactos(){

        return listadecontactos;
    }

}
