package com.example.firebasecrud

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var mRecyclerView: RecyclerView? = null
    var mNewFloatingActionButton: FloatingActionButton? = null
    var mCharacterAdapter: CharacterAdapter? = null
    var mLayoutManager: LinearLayoutManager? = null
    private var mListProduct: ArrayList<Product?>? = null
    private var mDatabaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FindViews()
        mListProduct = ArrayList()
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("character")
        mNewFloatingActionButton!!.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        }
        Recycler()
    }

    private fun FindViews() {
        mRecyclerView = findViewById(R.id.characterRecyclerView)
        mNewFloatingActionButton = findViewById(R.id.newFloatingActionButton)
    }

    fun Recycler() {
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager
        mCharacterAdapter = CharacterAdapter(mListProduct)
        mRecyclerView!!.adapter = mCharacterAdapter
        Content()
        deleteSwipe()
    }

    private fun Content() {
        mDatabaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mListProduct!!.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val product = postSnapshot.getValue(Product::class.java)
                    if (product != null) {
                        product.key = postSnapshot.key
                    }
                    mListProduct!!.add(product)
                }
                mCharacterAdapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deleteSwipe() {
        val touchHelperCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mDatabaseReference!!.child(mListProduct!![viewHolder.adapterPosition]!!.key!!).setValue(null)
                mCharacterAdapter!!.deleteItem(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(touchHelperCallback)
        itemTouchHelper.attachToRecyclerView(mRecyclerView)
    }
}