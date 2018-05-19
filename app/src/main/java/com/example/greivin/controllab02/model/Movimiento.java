package com.example.greivin.controllab02.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.greivin.controllab02.contract.MovimientoContract;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Greivin on 7/4/2018.
 */

public class Movimiento implements Serializable {

    private long _id;
    private String descripcion;
    private Date fecha;
    private Categoria categoria;

    public Movimiento(){
        this._id=0;
        this.descripcion="";
        this.fecha = new Date();
        this.categoria = null;
    }

    public Movimiento(long _id, String descripcion, Date fecha, Categoria categoria) {
        this._id = _id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();

        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int year = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String fechaString = year+"-"+mes+"-"+day;

        values.put(MovimientoContract.MovimientoEntry.CATEGORIA,categoria.get_id());
        values.put(MovimientoContract.MovimientoEntry.DESCRIPCION,descripcion);
        values.put(MovimientoContract.MovimientoEntry.FECHA,fechaString);

        return values;
    }


}