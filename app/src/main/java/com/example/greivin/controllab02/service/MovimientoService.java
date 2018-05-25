package com.example.greivin.controllab02.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.greivin.controllab02.contract.MovimientoContract;
import com.example.greivin.controllab02.dbHelper.MovimientoDbHelper;
import com.example.greivin.controllab02.model.Categoria;
import com.example.greivin.controllab02.model.Movimiento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Greivin on 14/5/2018.
 */

public class MovimientoService { //Un service por tabla

    Context context;
    MovimientoDbHelper movimientoDbHelper;

    public MovimientoService(Context context){
        this.context = context;
        movimientoDbHelper= new MovimientoDbHelper(context);
    }

    public void create(Movimiento movimiento){
        SQLiteDatabase sqLiteDatabase = movimientoDbHelper.getWritableDatabase();

        sqLiteDatabase.insert(
                MovimientoContract.MovimientoEntry.TABLE_NAME,null,movimiento.toContentValues());

    }

    public void delete (String llave){
        SQLiteDatabase sqLiteDatabase = movimientoDbHelper.getWritableDatabase();

        sqLiteDatabase.delete(MovimientoContract.MovimientoEntry.TABLE_NAME,"_id = ?",new String[]{llave});

    }

    public void update (Movimiento movimiento){
        SQLiteDatabase sqLiteDatabase = movimientoDbHelper.getWritableDatabase();

        //sqLiteDatabase.update(MovimientoContract.MovimientoEntry.TABLE_NAME)
    }

    public List<Movimiento> findAll() {
        SQLiteDatabase sqLiteDatabase = movimientoDbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                MovimientoContract.MovimientoEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        List<Movimiento> movimientos = new ArrayList();
        Movimiento movimiento;
        while (cursor.moveToNext()) {
            movimiento = new Movimiento();
            movimiento.set_id(cursor.getInt(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry._ID)));

            int numCategoria = cursor.getInt(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.CATEGORIA));
            CategoriaService categoriaService = new CategoriaService(context);
            Categoria categoria = categoriaService.getCategoriaBYID(numCategoria);
            movimiento.setCategoria(categoria);

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            String dateString =cursor.getString(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.FECHA));
            try {
                date = format.parse(dateString);
            } catch (Exception e) {
                date = new Date();
                e.printStackTrace();
            }
            movimiento.setFecha(date);
            movimiento.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow(MovimientoContract.MovimientoEntry.DESCRIPCION)));
            movimientos.add(movimiento);
        }

        return movimientos;

    }

}
