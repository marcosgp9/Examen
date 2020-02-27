package com.example.examen;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EliminarContacto extends AppCompatActivity {

    ConsultarUsuario cU = new ConsultarUsuario();
    EditText campoNombre;
    String nombreBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);


        campoNombre= (EditText) findViewById(R.id.eTNombreBorrar);

    }

    public void onClick(View view) {
        eliminarUsuario();
    }




    private void eliminarUsuario() {
        AdminSQLite admin =new AdminSQLite(this,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        int cantidad;
        nombreBorrar = campoNombre.getText().toString();

        if(!nombreBorrar.isEmpty()){

            cantidad = admin.borrarContacto(db, nombreBorrar);

            campoNombre.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "El contacto ha sido eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();
            }

        }

        db.close();
    }

}