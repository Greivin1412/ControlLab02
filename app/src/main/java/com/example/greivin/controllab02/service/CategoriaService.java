package com.example.greivin.controllab02.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.greivin.controllab02.contract.CategoriaContract;
import com.example.greivin.controllab02.dbHelper.CategoriaDbHelper;
import com.example.greivin.controllab02.model.Categoria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greivin on 18/5/2018.
 */

public class CategoriaService {

    Context context;
    CategoriaDbHelper categoriaDbHelper;

    public CategoriaService(Context context){
        this.context = context;
        categoriaDbHelper= new CategoriaDbHelper(context);
    }

    public void create(Categoria categoria){
        SQLiteDatabase sqLiteDatabase = categoriaDbHelper.getWritableDatabase();

        sqLiteDatabase.insert(
                CategoriaContract.CategoriaEntry.TABLE_NAME,null,categoria.toContentValues());

    }

    public void delete (int llave){
        SQLiteDatabase sqLiteDatabase = categoriaDbHelper.getWritableDatabase();
    }

    public void update (Categoria categoria){
        SQLiteDatabase sqLiteDatabase = categoriaDbHelper.getWritableDatabase();

        //sqLiteDatabase.update(CategoriaContract.CategoriaEntry.TABLE_NAME)
    }

    public List<Categoria> findAll() {
        SQLiteDatabase sqLiteDatabase = categoriaDbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                CategoriaContract.CategoriaEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        List<Categoria> categorias = new ArrayList();
        Categoria categoria;
        while (cursor.moveToNext()) {
            categoria = new Categoria();
            categoria.set_id(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriaEntry._ID)));
            categoria.setTipoGasto(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriaEntry.TIPO_GASTO)));
            categoria.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriaEntry.DESCRIPCION)));
            categorias.add(categoria);

        }

        return categorias;

    }

    public Categoria getCategoriaBYID(int id){
        Categoria categoria= new Categoria();
        SQLiteDatabase db = categoriaDbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                CategoriaContract.CategoriaEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                "_id = ?",              // The columns for the WHERE clause
                new String[]{String.valueOf(id)},          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        if(cursor.moveToFirst()){

            categoria.set_id(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriaEntry._ID)));
            categoria.setTipoGasto(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriaEntry.TIPO_GASTO)));
            categoria.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow(CategoriaContract.CategoriaEntry.DESCRIPCION)));
        }

        return categoria;
    }
}
