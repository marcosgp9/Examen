package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdminSQLite conn = new AdminSQLite(this, "administracion", null, 1);
    }

    public void onClick(View view){
        Intent miIntent = null;
        switch (view.getId()){
            case R.id.bMenuRegistrar:
                miIntent = new Intent (MainActivity.this,RegistroUsuarioActivity.class);
                break;
            case R.id.bMenuConsultar:
                miIntent = new Intent(MainActivity.this, ConsultarUsuario.class);
                break;
            case R.id.bMenuEliminar:
                miIntent = new Intent(MainActivity.this, EliminarContacto.class);
                break;
            case R.id.bMenuModificar:
                miIntent = new Intent(MainActivity.this, ModificarContacto.class);
                break;
        }
        if(miIntent != null){
            startActivity(miIntent);
        }
    }


}