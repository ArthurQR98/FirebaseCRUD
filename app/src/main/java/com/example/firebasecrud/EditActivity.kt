package com.example.firebasecrud

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.lang.String
import java.util.*

class EditActivity : AppCompatActivity() {
    @JvmField
    var mdescripcionEditText: EditText? = null
    @JvmField
    var mcategoriaEditText: EditText? = null
    @JvmField
    var mUrlEditText: EditText? = null
    @JvmField
    var mprecioEditText: EditText? = null
    @JvmField
    var mstockEditText: EditText? = null
    @JvmField
    var mCharacterButton: Button? = null
    private var mDatabaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        FindViews()
        val mKey = Objects.requireNonNull(intent.extras)?.getString("key")
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("character").child(mKey!!)
        mDatabaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val character = dataSnapshot.getValue(Product::class.java)
                if (character!!.descripcion != null) {
                    mdescripcionEditText!!.setText(character.descripcion)
                }
                if (character.categoria != null) {
                    mcategoriaEditText!!.setText(character.categoria)
                }
                if (character.precio != null) {
                    mprecioEditText!!.setText(character.precio.toString())
                }
                mstockEditText!!.setText(String.valueOf(character.stock))
                mUrlEditText!!.setText(String.valueOf(character.url))
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EditActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        mCharacterButton!!.setOnClickListener { v: View? ->
            mDatabaseReference!!.child("descripcion").setValue(mdescripcionEditText!!.text.toString())
            mDatabaseReference!!.child("categoria").setValue(mcategoriaEditText!!.text.toString())
            mDatabaseReference!!.child("precio").setValue(java.lang.Float.valueOf(mprecioEditText!!.text.toString()))
            mDatabaseReference!!.child("stock").setValue(mstockEditText!!.text.toString().toInt())
            mDatabaseReference!!.child("url").setValue(mUrlEditText!!.text.toString().toInt())
            finish()
        }
    }

    fun FindViews() {
        mdescripcionEditText = findViewById(R.id.descripcionEditText)
        mcategoriaEditText = findViewById(R.id.categoriaEditText)
        mUrlEditText = findViewById(R.id.urlEditText)
        mprecioEditText = findViewById(R.id.precioEditText)
        mstockEditText = findViewById(R.id.stockEditText2)
        mCharacterButton = findViewById(R.id.productButton)
    }
}