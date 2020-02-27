package com.example.examen;

public class Contacte {
    String id;
    String nombre;
    String completado;

    public Contacte(String id, String nombre, String completado) {
        this.id = id;
        this.nombre = nombre;
        this.completado = completado;
    }

    public Contacte(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCompletado() {
        return completado;
    }

    public void setCompletado(String completado) {
        this.completado = completado;
    }
}