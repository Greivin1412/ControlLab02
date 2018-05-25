package com.example.greivin.controllab02.contract;

import android.provider.BaseColumns;

/**
 * Created by Greivin on 14/5/2018.
 */

public final class MovimientoContract {

    private MovimientoContract(){}

    public static abstract class MovimientoEntry implements BaseColumns {
        public static final String TABLE_NAME = "movimiento";

        public static final String _ID = "_id";
        public static final String CATEGORIA = "categoria";
        public static final String DESCRIPCION = "descripcion";
        public static final String FECHA = "fecha";

    }
}
