package org.unimag.api;

import java.util.List;

public interface ApiOperacionBD<T, ID> {

    public int getSerial();

    public T insertInto(T objeto, String ruta);

    public List<T> selectFrom();
    //SON 30 PUNTOS!!!! NO LO BORRES 
    //public List<T> selectFromWhereActivos();

    public int numRows();

    public Boolean delectFrom(ID coidgo);

    public T updateSet(ID codigo, T objeto, String ruta);

    public T getOne(ID codigo);

}

