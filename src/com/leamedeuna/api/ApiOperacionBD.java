package com.leamedeuna.api;

import java.util.List;

public interface ApiOperacionBD<T, ID> {

    int getSerial();

    T insertInto(T objeto, String ruta);

    List<T> selectFrom();

    int numRows();

    Boolean deleteFrom(ID codigo);

    T updateSet(ID codigo, T objeto, String ruta);

    T getOne(ID codigo);
}