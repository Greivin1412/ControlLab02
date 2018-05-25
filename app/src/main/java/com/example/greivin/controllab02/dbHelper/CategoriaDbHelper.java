package com.example.greivin.controllab02.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.greivin.controllab02.contract.CategoriaContract;

/**
 * Created by Greivin on 18/5/2018.
 */

public class CategoriaDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME="Categorias.db";

    public CategoriaDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ CategoriaContract.CategoriaEntry.TABLE_NAME+" ("+
                CategoriaContract.CategoriaEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                CategoriaContract.CategoriaEntry.TIPO_GASTO+ " INTEGER NOT NULL, "+
                CategoriaContract.CategoriaEntry.DESCRIPCION+ " TEXT NOT NULL, "+
                "UNIQUE (" + CategoriaContract.CategoriaEntry._ID+ "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
