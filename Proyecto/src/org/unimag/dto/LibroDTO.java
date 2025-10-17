package org.unimag.dto;

import java.text.DecimalFormat;

public class LibroDTO {
    private String id;
    private String nombre;
    private String precio;
    private int anio;
    private String editorial;
    private String autor;

    public LibroDTO(String id, String nombre, Double precio, int anio, String editorial, String pais, String autor, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.precio = new DecimalFormat("#.00").format(precio);
        this.anio = anio;
        this.editorial = editorial + " (" + pais + ")";
        this.autor = autor + " (" + ("true".equalsIgnoreCase(genero) ? "Masculino" : "Femenino") + ")";
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = new DecimalFormat("#.00").format(precio);
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}