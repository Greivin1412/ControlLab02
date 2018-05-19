package com.example.greivin.controllab02.controllab02;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.greivin.controllab02.ListAdapter;
import com.example.greivin.controllab02.R;
import com.example.greivin.controllab02.model.Categoria;
import com.example.greivin.controllab02.model.Movimiento;
import com.example.greivin.controllab02.service.CategoriaService;
import com.example.greivin.controllab02.service.MovimientoService;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    ListView listView;
    ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),ListAddActivity.class);
              //  intent.putExtra("lista",movimientos);
                startActivity(intent);
                finish();
            }
        });

        listView = (ListView)findViewById(R.id.list);
        MovimientoService movimientoService = new MovimientoService(this);
        final ArrayList  movimientos = (ArrayList)movimientoService.findAll();
        adapter = new ListAdapter(movimientos,getApplicationContext());
        listView.setAdapter(adapter);




      /* Bundle objeto = getIntent().getExtras();

       if(objeto!=null){
          movimientos = (ArrayList) objeto.getSerializable("mov");
          if(movimientos==null){
              movimientos=(ArrayList)movimientoService.findAll();;
          }
       }*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
