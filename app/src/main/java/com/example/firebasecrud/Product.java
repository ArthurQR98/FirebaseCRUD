package com.example.firebasecrud;

import com.google.firebase.database.Exclude;

public class Product {

    private String key;
    private String descripcion;
    private String categoria;
    private Float precio;
    private int stock;
    private int url;

    public Product() {
    }


    public Product(String descripcion, String categoria, Float precio, int stock, int url) {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.stock=stock;
        this.url = url;



    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) { this.precio = precio;}


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) { this.stock =stock;}


    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
