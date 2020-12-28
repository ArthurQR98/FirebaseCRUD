package com.example.firebasecrud

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*

class DetailActivity : AppCompatActivity() {
    // es lo que tendra el activity detail, no va todos los datos solo nombre y descripcion
    @JvmField
    var mCharacterImageView: ImageView? = null
    @JvmField
    var mBackgroundImageView: ImageView? = null
    @JvmField
    var mdescripcionTextView: TextView? = null
    @JvmField
    var mDescriptionTextView: TextView? = null
    private var mDatabaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        FindViews()
        val mKey = Objects.requireNonNull(intent.extras)?.getString("key")
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("character").child(mKey!!)
        mDatabaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val charater = dataSnapshot.getValue(Product::class.java)
                if (charater!!.descripcion != null) {
                    mdescripcionTextView!!.text = charater.descripcion
                }
                if (charater.precio != null) {
                    mDescriptionTextView!!.text = charater.precio.toString()
                }
                val charactersImages = intArrayOf(R.drawable.cocacola, R.drawable.comida, R.drawable.maquillaje, R.drawable.terno)
                val backgroundsImages = intArrayOf(R.drawable.cocacola_background, R.drawable.comida_background, R.drawable.maquillaje_background, R.drawable.terno_background)
                if (charater.url < 4) {
                    mCharacterImageView!!.setImageResource(charactersImages[charater.url])
                }
                if (charater.url < 4) {
                    mBackgroundImageView!!.setImageResource(backgroundsImages[charater.url])
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun FindViews() {
        mCharacterImageView = findViewById(R.id.characterImageView)
        mBackgroundImageView = findViewById(R.id.backgroundImageView)
        mdescripcionTextView = findViewById(R.id.descripcionTextView2)
        mDescriptionTextView = findViewById(R.id.descriptionTextView)
    }
}