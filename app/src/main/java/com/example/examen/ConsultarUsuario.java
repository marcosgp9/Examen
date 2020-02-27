package com.example.examen;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.LinkedList;

public class ConsultarUsuario extends AppCompatActivity {

    ArrayList<Contacte> listaContactos;

    RadioButton radioButton;
    RadioGroup radioGroup;

    private RecyclerView.LayoutManager layoutManager;
    private LinkedList<Contacte> mWorldLinked = new LinkedList<>();
    String id, nombre,numero;
    Button dialog;
    Dialog thisDialog;
    String[] opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        conseguirContactos();
        radioGroup = findViewById(R.id.groupid);


        insertarElementos();
        //visualizarNoCompletadas();
        //visualizarSiCompletadas();

        //Dialog

        dialog();


    }

    public void conseguirContactos(){
        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        listaContactos = admin.conseguirListaContactos();
    }

    public void guardarNuevo(View view){
        Intent miIntent = new Intent(ConsultarUsuario.this, RegistroUsuarioActivity.class);
        startActivity(miIntent);
    }

    public void modificarContacto(View view){
        Intent miIntent = new Intent(ConsultarUsuario.this, ModificarContacto.class);
        startActivity(miIntent);
    }

    public void insertarElementos(){
        conseguirContactos();

        for(int i = 0; i < listaContactos.size(); i++){
            String nombre = listaContactos.get(i).getNombre();
        }

        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        for(int i = 0; i < listaContactos.size(); i++){
            this.mWorldLinked.add(listaContactos.get(i));
        }

        RecyclerView.Adapter mAdapter = new WordListAdapter(this, mWorldLinked);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
/*
    private View.OnTouchListener Spinner_OnTouch = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Intent miIntent = new Intent(ConsultarUsuario.this, RegistroUsuarioActivity.class);
                startActivity(miIntent);
            }
            return true;
        }
    };
*/

    public String[] insertarOpciones(){
        String[] listaOpciones = new String[3];
        listaOpciones[0] = "Lista Contactos";
        listaOpciones[1] = "Modificar Usuario";
        listaOpciones[2] = "Eliminar Usuario";
        return listaOpciones;
    }

    public void dialog(){
        dialog = (Button)findViewById(R.id.guardarNuevoPlaneta);

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thisDialog = new Dialog(ConsultarUsuario.this);
                thisDialog.setContentView(R.layout.dialog_signin);

                final EditText eTId = (EditText)thisDialog.findViewById(R.id.dialogId);
                final EditText eTNombre= (EditText)thisDialog.findViewById(R.id.dialogNombre);

                Button savePlanet = (Button)thisDialog.findViewById(R.id.guardar);

                eTId.setEnabled(true);
                eTNombre.setEnabled(true);

                savePlanet.setEnabled(true);

                thisDialog.show();

                savePlanet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        id = eTId.getText().toString();
                        nombre = eTNombre.getText().toString();

                        thisDialog.cancel();

                        guardarNuevo(id, nombre);
                        Intent miIntent = new Intent(ConsultarUsuario.this, ConsultarUsuario.class);
                        startActivity(miIntent);
                    }
                });

            }
        });
    }

    public void guardarNuevo(String id, String nombre){
        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        admin.guarnarNuevoContacto(BaseDeDatos, id, nombre);
    }


    public void visualizarNoCompletadas(){


    }

    public void visualizarSiCompletadas(){


    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.verNo:
                if (checked)

                break;
            case R.id.verSi:
                if (checked)

                break;
            case R.id.verTodas:
                if (checked)

                break;
        }
    }


}