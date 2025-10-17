package org.unimag.modelo;

public class Genero {
    private Integer idGenero;
    private String nombreGenero;
    private Boolean estadoGenero;

    public Genero() {
    }

    public Genero(Integer idGenero, String nombreGenero, Boolean estadoGenero) {
        this.idGenero = idGenero;
        this.nombreGenero = nombreGenero;
        this.estadoGenero = estadoGenero;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public Boolean getEstadoGenero() {
        return estadoGenero;
    }

    public void setEstadoGenero(Boolean estadoGenero) {
        this.estadoGenero = estadoGenero;
    }

    @Override
    public String toString() {
        return "Genero" 
                + "idGenero=" + idGenero 
                + "nombreGenero=" + nombreGenero 
                + "estadoGenero=" + estadoGenero;
    }
    
    

}

