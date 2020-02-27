package com.example.examen;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroUsuarioActivity extends AppCompatActivity {

    EditText campoId, campoNombre;
    String idGuardar, nombreGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        campoId = (EditText) findViewById(R.id.campoId);
        campoNombre= (EditText) findViewById(R.id.campoNombre);
        //campoTelefono= (EditText) findViewById(R.id.campoTelefono);

    }

    public void onClick(View view) {

        registrarUsuarios();
        Intent miIntent = new Intent(RegistroUsuarioActivity.this, ConsultarUsuario.class);
        startActivity(miIntent);
    }




    private void registrarUsuarios() {
        AdminSQLite admin =new AdminSQLite(this,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        idGuardar = campoId.getText().toString();
        nombreGuardar = campoNombre.getText().toString();
        //telefonoGuardar = campoTelefono.getText().toString();

        admin.guardarContacto(db, idGuardar, nombreGuardar, "no");

        Toast.makeText(getApplicationContext(),"Id Registro: "+ nombreGuardar,Toast.LENGTH_SHORT).show();
        db.close();
    }

}

