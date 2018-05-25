package com.example.greivin.controllab02.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.greivin.controllab02.contract.MovimientoContract;

/**
 * Created by Greivin on 14/5/2018.
 */

public class MovimientoDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME="Movimientos.db";

    public MovimientoDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "+ MovimientoContract.MovimientoEntry.TABLE_NAME+" ("+
                MovimientoContract.MovimientoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                MovimientoContract.MovimientoEntry.CATEGORIA + " INTEGER NOT NULL, "+
                MovimientoContract.MovimientoEntry.DESCRIPCION + " TEXT NOT NULL, "+
                MovimientoContract.MovimientoEntry.FECHA + " DATE NOT NULL, "+
                "UNIQUE (" + MovimientoContract.MovimientoEntry._ID + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
