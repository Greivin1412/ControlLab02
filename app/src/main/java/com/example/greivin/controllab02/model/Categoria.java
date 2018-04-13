package com.example.greivin.controllab02.model;

import java.io.Serializable;

/**
 * Created by Greivin on 7/4/2018.
 */

public class Categoria implements Serializable{

    private long idCategoria;
    //0 = ingreso  1 = gasto
    private int tipoGasto;
    private String descripcion;

    public Categoria(long idCategoria, int tipoGasto, String descripcion) {
        this.idCategoria = idCategoria;
        this.tipoGasto = tipoGasto;
        this.descripcion = descripcion;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(int tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return  descripcion;
    }
}
