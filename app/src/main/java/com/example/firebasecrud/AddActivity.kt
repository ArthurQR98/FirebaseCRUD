package com.example.firebasecrud

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {
    @JvmField
    var mdescripcionEditText: EditText? = null
    @JvmField
    var mcategoriaEditText: EditText? = null
    @JvmField
    var mprecioEditText: EditText? = null
    @JvmField
    var mstockEditText: EditText? = null
    @JvmField
    var mUrlEditText: EditText? = null
    @JvmField
    var mproductButton: Button? = null
    private var mDatabaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        FindsViews()

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("character")
        mproductButton!!.setOnClickListener { v: View? ->
            val descripcion = mdescripcionEditText!!.text.toString()
            val categoria = mcategoriaEditText!!.text.toString()
            val precio = mprecioEditText!!.text.toString().toFloat()
            val stock = mstockEditText!!.text.toString().toInt()
            val url = mUrlEditText!!.text.toString().toInt()
            val mProduct = Product(descripcion, categoria, precio, stock, url)
            val id = mDatabaseReference!!.push().key
            if (id != null) {
                mDatabaseReference!!.child(id).setValue(mProduct)
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun FindsViews() {
        mdescripcionEditText = findViewById(R.id.descripcionEditText)
        mcategoriaEditText = findViewById(R.id.categoriaEditText)
        mprecioEditText = findViewById(R.id.precioEditText)
        mstockEditText = findViewById(R.id.stockEditText)
        mUrlEditText = findViewById(R.id.urlEditText)
        mproductButton = findViewById(R.id.productButton)
    }
}