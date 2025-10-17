package org.unimag.modelo;

public class Libro {

    private Integer idLibro;
    private String nombreLibro;
    private Double precioLibro;
    private Short anioLibro;
    private Editorial idEditorial;
    private Autor idAutor;

    @Override
    public String toString() {
        return "Libro{" + "idLibro=" + idLibro + ", nombreLibro=" + nombreLibro + ", precioLibro=" + precioLibro + ", anioLibro=" + anioLibro + ", idEditorial=" + idEditorial + ", idAutor=" + idAutor + '}';
    }

    public Libro() {
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public Double getPrecioLibro() {
        return precioLibro;
    }

    public void setPrecioLibro(Double precioLibro) {
        this.precioLibro = precioLibro;
    }

    public Short getAnioLibro() {
        return anioLibro;
    }

    public void setAnioLibro(Short anioLibro) {
        this.anioLibro = anioLibro;
    }

    public Editorial getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(Editorial idEditorial) {
        this.idEditorial = idEditorial;
    }

    public Autor getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Autor idAutor) {
        this.idAutor = idAutor;
    }
}

