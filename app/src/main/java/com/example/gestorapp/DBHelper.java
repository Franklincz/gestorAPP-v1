package com.example.gestorapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //Name of databse
    public static final String DBName = "EMPRESA.DB";
    // Nombre de la tabla
    public static final String TABLE_NAME = "USERS";
    public static final String TABLE_NAME1 = "PUNTOVENTA";
    public static final String TABLE_NAME2 = "PRODUCTOS";
    // Columnas de la tabla
    public static final String CODIGO = "codigo";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NOMBRES = "nombres";
    public static final String APELLIDOS = "apellidos";
    public static final String CORREO = "correo";
    public static final String IMAGEN = "imagen";
// columnas de la tabla 1


    public static final String NAMECODIGO = "namecodigo";
    public static final String CODIGO1 = "codigo";
    public static final String FOTO = "foto";
    public static final String DESCRIPTION = "descripcion";
    public static final String DIRECCION = "direccion";


    //columnas para la tabla 2
    public static final String CODIGO2 = "codigo";
    public static final String DESCRIPCION = "descripcion";
    public static final String COSTO = "costo";
    public static final String PRECIORVTAMAYOR = " precioRvtaMayor";
    public static final String STOCK = "stock", PUNTOVENTAID = "puntoVentaID";


    // Versión de la base de datos(importante)
    static final int DB_VERSION = 1;


    // Script para la creación de la tabla
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USERNAME + " TEXT NOT NULL, "
            + PASSWORD + " TEXT NOT NULL, "
            + NOMBRES + " TEXT NOT NULL, "
            + APELLIDOS + " TEXT NOT NULL, "
            + CORREO + " TEXT NOT NULL, "
            + IMAGEN + " BLOB );";


    //table 1
    private static final String CREATE_TABLE1 = "create table " + TABLE_NAME1 + "("
            + CODIGO1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAMECODIGO + " TEXT NOT NULL, "
            + DESCRIPTION + " TEXT NOT NULL, "
            + DIRECCION + " TEXT NOT NULL, "
            + FOTO + " BLOB );";

    //table 2
    private static final String CREATE_TABLE2 = "create table " + TABLE_NAME2 + "("
            + CODIGO2 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DESCRIPCION + " TEXT NOT NULL, "
            + COSTO + " REAL NOT NULL, "
            + PRECIORVTAMAYOR + " REAL NOT NULL, "
            + STOCK + " INTEGER NOT NULL, "
            + PUNTOVENTAID + " INTEGER REFERENCES " + TABLE_NAME1 + "(" + CODIGO1 + ")" + ");";


    public DBHelper(Context context) {
        super(context, DBName, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL(CREATE_TABLE);
        myDB.execSQL(CREATE_TABLE1);
        myDB.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        myDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        myDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(myDB);
    }
}
