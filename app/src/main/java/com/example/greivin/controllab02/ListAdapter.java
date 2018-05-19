package com.example.greivin.controllab02;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greivin.controllab02.model.Movimiento;
import com.example.greivin.controllab02.service.MovimientoService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Greivin on 7/4/2018.
 */

public class ListAdapter extends ArrayAdapter<Movimiento> implements View.OnClickListener{

    private ArrayList<Movimiento> dataSet;
    Context mContext;//Esto el el contexto de la actividad principal(main Activity)

    public ListAdapter(ArrayList<Movimiento> data, Context context){
        super(context,R.layout.list_item,data);//Le decimos cual es el Layout de este Adapter
        this.dataSet=data;
        this.mContext = context;
    }

    //setear los datos en la lista
    @Override
    public View getView (int position, View convertView, ViewGroup parent){//Segun la cantidad de la lista

        Movimiento dataModel = getItem(position);

        if(convertView == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.list_item,null);
        }

    //parent.setBackgroundColor(R.color.colorAccent);
        //Obtenemos las instancias de los elementos de la lista
        ImageView categoria = (ImageView)convertView.findViewById(R.id.listTipo);
        TextView descripcion = (TextView)convertView.findViewById(R.id.listDescripcion);
        TextView fecha = (TextView)convertView.findViewById(R.id.listFecha);
        ImageButton boton = (ImageButton)convertView.findViewById(R.id.listBoton);
//        parent.getChildAt(position).setBackgroundColor(Color.BLUE);
  /*  if(dataModel.getCategoria().getTipoGasto()==0) {
        categoria.setImageResource(android.R.drawable.presence_online);
    }else{
        categoria.setImageResource(R.drawable.red);
    }*/
        descripcion.setText(dataSet.get(position).getDescripcion());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fech = formato.format(dataSet.get(position).getFecha());
        fecha.setText(fech);//Aqui hay que hacerle un Format Day
        boton.setImageResource(android.R.drawable.ic_delete);
        boton.setOnClickListener(this);//Es listener es esta misma clase xq pusimos el implements listener
        boton.setTag(position);
        return convertView;
    }

    @Override//Escucha el clic de todo lo que esta dibujado (el txtview,elboton,las imagenes, ect)
    public void onClick(View view) {
        //Elemento de la lista que se le dio click
        int position = (Integer)view.getTag();
        Object object = getItem(position); //tambien con el dataSet.getPosition(position)
        Movimiento movimiento = (Movimiento)object;

        switch (view.getId()){
            case R.id.listBoton:
                //Eliminar del boton
                MovimientoService movimientoService = new MovimientoService(mContext);
                movimientoService.delete(String.valueOf(movimiento.get_id()));
               dataSet.remove(position);// Remove a elemento de la lista
                this.notifyDataSetChanged();
                Toast.makeText(mContext,"Elemento Eliminado",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
