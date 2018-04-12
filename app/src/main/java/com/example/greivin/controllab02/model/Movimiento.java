package com.example.greivin.controllab02.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Greivin on 7/4/2018.
 */

public class Movimiento implements Serializable {

    private long idMovimiento;
    private String descripcion;
    private Date fecha;
    private Categoria categoria;

    public Movimiento(long idMovimiento, String descripcion, Date fecha, Categoria categoria) {
        this.idMovimiento = idMovimiento;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    public long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(long idMovimiento) {
        this.idMovimiento = idMovimiento;
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


    protected Movimiento(Parcel in) {
        idMovimiento = in.readLong();
        descripcion = in.readString();
        long tmpFecha = in.readLong();
        fecha = tmpFecha != -1 ? new Date(tmpFecha) : null;
        categoria = (Categoria) in.readValue(Categoria.class.getClassLoader());
    }

 /*  @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idMovimiento);
        dest.writeString(descripcion);
        dest.writeLong(fecha != null ? fecha.getTime() : -1L);
        dest.writeValue(categoria);
    }*/

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movimiento> CREATOR = new Parcelable.Creator<Movimiento>() {
        @Override
        public Movimiento createFromParcel(Parcel in) {
            return new Movimiento(in);
        }

        @Override
        public Movimiento[] newArray(int size) {
            return new Movimiento[size];
        }
    };
}