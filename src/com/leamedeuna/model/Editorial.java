package com.leamedeuna.model;

public class Editorial {

    private int id;
    private String nombre;
    private String pais;
    private int formato;
    private int cantidadLibros;

    public Editorial() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getFormato() {
        return formato;
    }

    public void setFormato(int formato) {
        this.formato = formato;
    }

    public int getCantidadLibros() {
        return cantidadLibros;
    }

    public void setCantidadLibros(int cantidadLibros) {
        this.cantidadLibros = cantidadLibros;
    }
}