package com.example.greivin.controllab02.controllab02;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.greivin.controllab02.R;
import com.example.greivin.controllab02.model.Categoria;
import com.example.greivin.controllab02.model.Movimiento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListAddActivity extends AppCompatActivity {

    List<Categoria> categoriaIngreso =  new ArrayList<Categoria>();
    List<Categoria> categoriaEgreso =  new ArrayList<Categoria>();
    ArrayList<Movimiento> lista;
    EditText fecha,descripcion;
    Spinner sItems;
    ArrayAdapter<Categoria> adapterIngreso,adapterEgreso;
    int dia,mes,ano;

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

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
            Date convertedDate = new Date();
            try {
                convertedDate = formato.parse(fecha.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Movimiento movimiento = new Movimiento(1, descripcion.getText().toString(), convertedDate, (Categoria) sItems.getSelectedItem());
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

    public void calendario(View view) {

        fecha = (EditText)findViewById(R.id.fechaTxt);

        final Calendar calendar = Calendar.getInstance();
        dia= calendar.get(Calendar.DAY_OF_MONTH);
        mes= calendar.get(Calendar.MONTH);
        ano= calendar.get(Calendar.YEAR);
        DatePickerDialog picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                fecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
            }
        }
                ,ano,mes,dia);
        picker.show();
    }
}