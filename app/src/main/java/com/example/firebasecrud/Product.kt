package com.example.firebasecrud

import com.google.firebase.database.Exclude

class Product {
    @get:Exclude
    @set:Exclude
    var key: String? = null
    var descripcion: String? = null
    var categoria: String? = null
    var precio: Float? = null
    var stock = 0
    var url = 0

    constructor() {}
    constructor(descripcion: String?, categoria: String?, precio: Float?, stock: Int, url: Int) {
        this.descripcion = descripcion
        this.categoria = categoria
        this.precio = precio
        this.stock = stock
        this.url = url
    }
}