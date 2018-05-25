package com.example.greivin.controllab02.contract;

import android.provider.BaseColumns;

/**
 * Created by Greivin on 18/5/2018.
 */

public final class CategoriaContract {

    private CategoriaContract(){}

    public static abstract class CategoriaEntry implements BaseColumns{

        public static final String TABLE_NAME = "categoria";

        public static final String _ID = "_id";
        public static final String TIPO_GASTO = "tipoGasto";
        public static final String DESCRIPCION = "descripcion";


    }
}
