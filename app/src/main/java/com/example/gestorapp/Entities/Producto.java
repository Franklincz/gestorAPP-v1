package com.example.gestorapp.Entities;

public class Producto {
    int codigo;
    String descripcion;
    double costo;
    double precioRvtaMayor;
    int stock;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecioRvtaMayor() {
        return precioRvtaMayor;
    }

    public void setPrecioRvtaMayor(double precioRvtaMayor) {
        this.precioRvtaMayor = precioRvtaMayor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Producto(int codigo, String descripcion, double costo, double precioRvtaMayor, int stock) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.precioRvtaMayor = precioRvtaMayor;
        this.stock = stock;
    }

    public Producto(String descripcion, double costo, double precioRvtaMayor, int stock) {

        this.descripcion = descripcion;
        this.costo = costo;
        this.precioRvtaMayor = precioRvtaMayor;
        this.stock = stock;
    }
}
