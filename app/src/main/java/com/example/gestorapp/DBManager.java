package com.example.gestorapp;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.gestorapp.Entities.Producto;
import com.example.gestorapp.Entities.PuntoVenta;
import com.example.gestorapp.Entities.User;

import java.util.ArrayList;

public class DBManager {
    static private DBHelper dbHelper;
    static private SQLiteDatabase database;
    private Context context;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }


    public ArrayList<Integer> getDataPv(String sql) {
        Cursor cursor;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        this.open();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getInt(0));
        }
        this.close();
        return arrayList;

    }

    public ArrayList<PuntoVenta> fetch() {
        this.open();
        ArrayList<PuntoVenta> arrayList = new ArrayList<>();
        String[] columns = new String[]{DBHelper.CODIGO1, DBHelper.NAMECODIGO, DBHelper.DESCRIPCION
                , DBHelper.DIRECCION, DBHelper.FOTO};
        Cursor cursor = database.query(DBHelper.TABLE_NAME1, columns
                , null, null
                , null, null, null);
        while (cursor.moveToNext()) {
            int codigo = cursor.getInt(0);
            String namecodigo = cursor.getString(1);
            String descripcion = cursor.getString(2);
            String direccion = cursor.getString(3);
            byte[] imagen = cursor.getBlob(4);

            arrayList.add(new PuntoVenta(namecodigo, codigo, imagen, descripcion, direccion));

        }
        this.close();
        return arrayList;
    }


    //t(nombres,username,password,nombres,apellidos,email );
    public void insert(String username, String password, String nombres, String apellidos, String correo) {
        this.open();

        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.USERNAME, username);
        contentValue.put(DBHelper.PASSWORD, password);
        contentValue.put(DBHelper.NOMBRES, nombres);
        contentValue.put(DBHelper.APELLIDOS, apellidos);
        contentValue.put(DBHelper.CORREO, correo);

        database.insert(DBHelper.TABLE_NAME, null, contentValue);
        this.close();


    }


    public void insertPv(String direccion, String foto, String descripcion, String nameCodigo) {

        //  dbManager.insertPv(direccion, null, descripcion, namecodigo);
        this.open();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NAMECODIGO, nameCodigo);
        contentValue.put(DBHelper.FOTO, foto);
        contentValue.put(DBHelper.DESCRIPCION, descripcion);
        contentValue.put(DBHelper.DIRECCION, direccion);


        database.insert(DBHelper.TABLE_NAME1, null, contentValue);
        this.close();


    }

    public boolean createProduct(Producto product) {

        try {
            this.open();


            ContentValues contentValue = new ContentValues();
            contentValue.put(DBHelper.NAMECODIGO, product.getDescripcion());
            contentValue.put(DBHelper.COSTO, product.getCosto());
            contentValue.put(DBHelper.PRECIORVTAMAYOR, product.getPrecioRvtaMayor());
            contentValue.put(DBHelper.STOCK, product.getStock());


            database.insert(DBHelper.TABLE_NAME2, null, contentValue);
            this.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public User LoginUser(String Email, String pass) {
        User user = null;
        this.open();
        Cursor cursor;

        cursor = database.rawQuery("select * from " + DBHelper.TABLE_NAME, null, null);
        while (cursor.moveToNext()) {
            String email = cursor.getString(5);
            String nombre = cursor.getString(3);
            String appellidos = cursor.getString(4);
            String password = cursor.getString(2);
            int codigo = cursor.getInt(0);
            String username = cursor.getString(1);
            Log.e(TAG, "loginUser:User Login successful   " + email + " --" + password);
            if (email.equals(Email) && password.equals(pass)) {

                user= new User(codigo,username,password,nombre,appellidos,email,null);




            }

        }
        this.close();
       return user;
    }


    public void update(PuntoVenta pv) {
        this.open();


        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAMECODIGO, pv.getName());
        contentValues.put(DBHelper.FOTO, pv.getFoto());
        contentValues.put(DBHelper.DESCRIPCION, pv.getDescripcion());
        contentValues.put(DBHelper.DIRECCION, pv.getDireccion());
        int i = database.update(DBHelper.TABLE_NAME1
                , contentValues
                , DBHelper.CODIGO + " = " + pv.getCodigo()
                , null);
        this.close();
    }


}
