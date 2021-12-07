package ne.subgrupo_catorce.proyecto_final.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import ne.subgrupo_catorce.proyecto_final.entidades.Eventos;

public class DbEventos extends DbHelper{

    Context context;
    public DbEventos(Context context){
        super(context);
        this.context = context;
    }
    public long insertaEventos(String nombre, String titulo, String fecha, String descripcion, Double latitud, Double longitud){

        long id = 0;

        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Nombre",nombre);
            values.put("Titulo",titulo);
            values.put("Fecha",fecha);
            values.put("Descripcion",descripcion);
            values.put("Latitud",latitud);
            values.put("Longitud",longitud);


            id = db.insert(TABLA_EVENTOS,null,values);

        }catch(Exception ex){
            ex.toString();
        }
        return id;

    }
    public ArrayList<Eventos> mostrarEventos(){
        DbHelper dphelper = new DbHelper(context);
        SQLiteDatabase db = dphelper.getWritableDatabase();

        ArrayList<Eventos> listaEventos = new ArrayList<>();
        Eventos eventos = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT ID, Nombre, Titulo, Fecha, Descripcion, Latitud, Longitud FROM "+TABLA_EVENTOS,null);
        if(cursorContactos.moveToFirst()){
            do{

                eventos = new Eventos();
                eventos.setId(cursorContactos.getInt(0));
                eventos.setNombre(cursorContactos.getString(1));
                eventos.setTitulo(cursorContactos.getString(2));
                eventos.setFecha(cursorContactos.getString(3));
                eventos.setDescripcion(cursorContactos.getString(4));
                eventos.setLatitud(cursorContactos.getDouble(5));
                eventos.setLongitud(cursorContactos.getDouble(6));
                listaEventos.add(eventos);

            }while (cursorContactos.moveToNext());
        }
        cursorContactos.close();
        return listaEventos;
    }

    public Eventos verEventos(int id){
        DbHelper dphelper = new DbHelper(context);
        SQLiteDatabase db = dphelper.getWritableDatabase();

        Eventos eventos = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM "+TABLA_EVENTOS+" WHERE id ="+id+" LIMIT 1",null);
        if(cursorContactos.moveToFirst()){


                eventos = new Eventos();
                eventos.setId(cursorContactos.getInt(0));
                eventos.setNombre(cursorContactos.getString(1));
                eventos.setTitulo(cursorContactos.getString(2));
                eventos.setFecha(cursorContactos.getString(3));
                eventos.setDescripcion(cursorContactos.getString(4));
                eventos.setLatitud(cursorContactos.getDouble(5));
                eventos.setLongitud(cursorContactos.getDouble(6));


        }
        cursorContactos.close();
        return eventos;
    }
    public boolean editarEvento(long id, String nombre, String titulo, String fecha, String descripcion, Double latitud, Double longitud){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{
            db.execSQL(" UPDATE " + TABLA_EVENTOS + " SET nombre ='" + nombre + "', titulo ='" + titulo + "', fecha ='" + fecha + "', descripcion ='" + descripcion + "', latitud ='" + latitud + "', longitud ='" + longitud + "' WHERE id ='" + id + "' ");
            correcto = true;
            Log.d("EDIT", nombre);

        }catch(Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;

    }
    public boolean eliminarEvento(long id){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{
            db.execSQL(" DELETE FROM " + TABLA_EVENTOS + " WHERE id = '" + id + "' ");
            correcto = true;

        }catch(Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;

    }
}
