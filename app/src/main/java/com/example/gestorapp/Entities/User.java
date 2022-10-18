package com.example.gestorapp.Entities;

import java.io.Serializable;

public class User implements Serializable {
    int codigo;

    private String UserName,password , Nombres, Apellidos, Correo;

    private byte[] imagen;


    public User(int codigo, String userName, String password, String nombres, String apellidos, String correo, byte[] imagen) {
        this.codigo = codigo;
        UserName = userName;
        this.password = password;
        Nombres = nombres;
        Apellidos = apellidos;
        Correo = correo;
        this.imagen = imagen;
    }


    public User(String userName, String password) {
        UserName = userName;
        this.password = password;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        codigo = codigo;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
