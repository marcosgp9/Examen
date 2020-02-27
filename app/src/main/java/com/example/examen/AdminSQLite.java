package com.example.examen;

import android.content.ContentValues;
import android.content.Context;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminSQLite extends SQLiteOpenHelper {



    public AdminSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tasques(id text, nombre text, telefono text)");
        //db.execSQL(utilidades.crearTablaUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS usuarios");
        //onCreate(db);
    }

    public ArrayList<Contacte> conseguirListaContactos() {
        ArrayList<Contacte> listaPlaneta = new ArrayList<Contacte>();
        String query = "SELECT * FROM tasques order by nombre";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Contacte contacto = new Contacte();
                contacto.setId(cursor.getString(0));
                contacto.setNombre(cursor.getString(1));
                contacto.setCompletado(cursor.getString(2));
                listaPlaneta.add(contacto);
            } while (cursor.moveToNext());
        }
        return listaPlaneta;
    }


    public void guardarContacto(SQLiteDatabase baseDeDatos, String id, String nombre, String telefono){

        ContentValues registro = new ContentValues();

        registro.put("id", id);
        registro.put("nombre", nombre);
        registro.put("telefono", telefono);

        baseDeDatos.insert("tasques", null, registro);
        baseDeDatos.close();
    }

    public int borrarContacto(SQLiteDatabase baseDeDatos, String nombre){
        String[] array = new String[1];
        array[0] = nombre;
        int cantidad = baseDeDatos.delete("tasques", "nombre=?", array);
        baseDeDatos.close();

        return cantidad;

    }

    public int modificar(String nombre, String telefono, SQLiteDatabase baseDeDatos){

        String[] array = new String[1];
        array[0] = nombre;

        ContentValues registro = new ContentValues();

        registro.put("telefono", telefono);

        int cantidad = baseDeDatos.update("tasques", registro, "nombre=?", array);
        baseDeDatos.close();

        return cantidad;
    }

    public String[] Buscar(String codigo, SQLiteDatabase baseDeDatos){
        String[] parametros = new String[1];
        parametros[0] = codigo;
        String a[] = new String[2];
        Cursor fila = baseDeDatos.rawQuery
                ("select nombre, telefono from tasques where id =" + codigo, null);

        if(fila.moveToFirst()){
            a[0] = fila.getString(0);
            a[1] = fila.getString(1);
            baseDeDatos.close();
        }else{

        }

        baseDeDatos.close();

        return a;

    }

    public int EliminarDeBaseDeDatos(String codigo, SQLiteDatabase baseDeDatos){
    String[] parametros = new String[1];
    parametros[0] = codigo;
        int cantidad = baseDeDatos.delete("tasques", "id=?", parametros);

        baseDeDatos.close();
        return cantidad;

    }

    public void guarnarNuevoContacto(SQLiteDatabase baseDeDatos, String id, String nombre){

        ContentValues registro = new ContentValues();

        registro.put("id", id);
        registro.put("nombre", nombre);
        registro.put("telefono", "no");

        baseDeDatos.insert("tasques", null, registro);
        baseDeDatos.close();

    }


}