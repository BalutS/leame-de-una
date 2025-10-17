package org.unimag.dto;

public class AutorDTO {
    private String id;
    private String nombre;
    private String genero;
    private int cantidadLibros;

    public AutorDTO(String id, String nombre, String genero, int cantidadLibros) {
        this.id = id;
        this.nombre = nombre;
        this.genero = "true".equalsIgnoreCase(genero) ? "Masculino" : "Femenino";
        this.cantidadLibros = cantidadLibros;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = "true".equalsIgnoreCase(genero) ? "Masculino" : "Femenino";
    }

    public int getCantidadLibros() {
        return cantidadLibros;
    }

    public void setCantidadLibros(int cantidadLibros) {
        this.cantidadLibros = cantidadLibros;
    }
}