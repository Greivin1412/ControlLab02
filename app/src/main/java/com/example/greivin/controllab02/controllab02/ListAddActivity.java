package com.example.greivin.controllab02.controllab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.greivin.controllab02.R;
import com.example.greivin.controllab02.model.Categoria;
import com.example.greivin.controllab02.model.Movimiento;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListAddActivity extends AppCompatActivity {

    List<Categoria> categoriaIngreso =  new ArrayList<Categoria>();
    List<Categoria> categoriaEgreso =  new ArrayList<Categoria>();
    ArrayList<Movimiento> lista;
    EditText fecha,descripcion;
    Spinner sItems;
    ArrayAdapter<Categoria> adapterIngreso,adapterEgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("OnCreate de ADDActivity");
        categoriaIngreso.add(new Categoria(1,0,"Salario"));
        categoriaIngreso.add(new Categoria(2,0,"Alquiler"));
        categoriaEgreso.add(new Categoria(3,1,"Alimentos"));
        categoriaEgreso.add(new Categoria(4,1,"Diversión"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_add);

        lista = (ArrayList<Movimiento>)getIntent().getSerializableExtra("lista");

        adapterIngreso  = new ArrayAdapter<Categoria>(
                this, android.R.layout.simple_spinner_item, categoriaIngreso);

        adapterEgreso = new ArrayAdapter<Categoria>(
                this, android.R.layout.simple_spinner_item, categoriaEgreso);


        adapterIngreso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterEgreso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sItems= (Spinner)findViewById(R.id.comboCategorias);
        sItems.setAdapter(adapterIngreso);

    }

    public void guardar(View view) {
        fecha = (EditText) findViewById(R.id.fechaTxt);
        descripcion = (EditText) findViewById(R.id.descripcionTxt);

        if(fecha.getText().toString().equals("") || descripcion.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Existen Espacios Vacios",Toast.LENGTH_SHORT).show();
        }else {

            Movimiento movimiento = new Movimiento(1, descripcion.getText().toString(), new Date(fecha.getText().toString()), (Categoria) sItems.getSelectedItem());
            lista.add(movimiento);
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("mov", lista);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }

    public void radioClick(View view) {
       // sItems = (Spinner) findViewById(R.id.comboCategorias);
        boolean marcado = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radioIngreso:
                if(marcado){
                    sItems.setAdapter(adapterIngreso);
                }
                break;

            case R.id.radioGasto:
                if(marcado){
                    sItems.setAdapter(adapterEgreso);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),ListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("mov",lista);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}