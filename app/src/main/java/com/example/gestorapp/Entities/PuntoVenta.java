package com.example.gestorapp.Entities;

import java.io.Serializable;

public class PuntoVenta implements Serializable {

    private String nameCodigo;
    private int codigo;
    byte[] foto;
    private String descripcion;
    private String direccion;

    public PuntoVenta(String nameCodigo, int codigo, byte[] foto, String descripcion, String direccion) {
        //arrayList.add(new PuntoVenta(nameCodigo,codigo,imagen,descripcion,direccion));
        this.nameCodigo = nameCodigo;
        this.codigo = codigo;
        this.foto = foto;
        this.descripcion = descripcion;
        this.direccion = direccion;
    }

    public PuntoVenta() {

    }





    public PuntoVenta(String name, String descripcion, String direccion) {
        this.nameCodigo = name;

        this.descripcion = descripcion;
        this.direccion = direccion;

    }

    public String getName() {
        return nameCodigo;
    }

    public void setName(String name) {
        this.nameCodigo = name;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


}
