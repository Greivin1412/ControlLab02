package com.example.greivin.controllab02.model;

import android.content.ContentValues;

import com.example.greivin.controllab02.contract.CategoriaContract;

import java.io.Serializable;

/**
 * Created by Greivin on 7/4/2018.
 */

public class Categoria implements Serializable{

    private long _id;


    //0 = ingreso  1 = gasto
    private int tipoGasto;
    private String descripcion;

    public Categoria(){

    }

    public Categoria(long _id, int tipoGasto, String descripcion) {
        this._id = _id;
        this.tipoGasto = tipoGasto;
        this.descripcion = descripcion;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
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

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();

        values.put(CategoriaContract.CategoriaEntry.TIPO_GASTO,tipoGasto);
        values.put(CategoriaContract.CategoriaEntry.DESCRIPCION,descripcion);

        return values;
    }
}
