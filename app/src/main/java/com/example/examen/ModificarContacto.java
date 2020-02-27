package com.example.examen;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModificarContacto extends AppCompatActivity {

    TextView tVNombre;
    EditText eTId, eTNombre,eTTelefono;
    String sIdModificar, sNombreModificar, sTelefonoModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);


        eTId= (EditText) findViewById(R.id.etIdModificar);
       // eTNombre= (EditText) findViewById(R.id.eTNombreModificar);
        eTTelefono= (EditText) findViewById(R.id.etTelefonoModificar);
        tVNombre = (TextView) findViewById(R.id.eTNombreModificar);
    }

    public void Buscar(View view) {

        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String a[];

        String codigo = eTId.getText().toString();

        if (!codigo.isEmpty()) {

            a = admin.Buscar(codigo, baseDeDatos);

            if (a[0] != "") {
                tVNombre.setText(a[0]);
                eTTelefono.setText(a[1]);
            } else {
                Toast.makeText(this, "No existe el articulo", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes introducir el código del articulo", Toast.LENGTH_SHORT).show();

        }
    }

    public void modificar(View view){
        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        int cantidad;

        sNombreModificar = tVNombre.getText().toString();
        sTelefonoModificar = eTTelefono.getText().toString();

        if(!sNombreModificar.isEmpty() && !sTelefonoModificar.isEmpty()){
            cantidad = admin.modificar(sNombreModificar, sTelefonoModificar, baseDeDatos);

            if(cantidad == 1){
                Toast.makeText(this, "Articulo modificado correctamente", Toast.LENGTH_SHORT ).show();
                Intent miIntent = new Intent(ModificarContacto.this, ConsultarUsuario.class);
                startActivity(miIntent);
            }else{


            }

        }else{
            Toast.makeText(this,"Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void Eliminar(View view) {

        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String codigo = eTId.getText().toString();
        int cantidad;

        if (!codigo.isEmpty()) {

            cantidad = admin.EliminarDeBaseDeDatos(codigo, baseDeDatos);

            eTId.setText("");
            eTNombre.setText("");
            eTTelefono.setText("");

            if (cantidad == 1) {
                Toast.makeText(this, "Contacto eliminado exitosamente", Toast.LENGTH_SHORT).show();
                Intent miIntent = new Intent(ModificarContacto.this, ConsultarUsuario.class);
                startActivity(miIntent);
            } else {
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes de introducir el código del articulo", Toast.LENGTH_SHORT).show();
        }

    }
}
