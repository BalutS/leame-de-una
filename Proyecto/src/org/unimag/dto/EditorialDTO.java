package org.unimag.dto;

public class EditorialDTO {
    private String id;
    private String nombre;
    private String pais;
    private String formato;
    private int cantidadLibros;

    public EditorialDTO(String id, String nombre, String pais, String formato, int cantidadLibros) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.formato = formato;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFormato() {
        switch (formato) {
            case "1":
                return "Impreso";
            case "2":
                return "Digital";
            case "3":
                return "Impreso y Digital";
            default:
                return "Desconocido";
        }
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getCantidadLibros() {
        return cantidadLibros;
    }

    public void setCantidadLibros(int cantidadLibros) {
        this.cantidadLibros = cantidadLibros;
    }
}