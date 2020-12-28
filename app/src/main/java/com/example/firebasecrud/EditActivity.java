package com.example.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditActivity extends AppCompatActivity {

    @BindView(R.id.descripcionEditText)
    EditText mdescripcionEditText;

    @BindView(R.id.categoriaEditText)
    EditText mcategoriaEditText;

    @BindView(R.id.urlEditText)
    EditText mUrlEditText;

    @BindView(R.id.precioEditText)
    EditText mprecioEditText;

    @BindView(R.id.stockEditText)
    EditText mstockEditText;

    @BindView(R.id.productButton)
    Button mCharacterButton;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        String mKey= Objects.requireNonNull(getIntent().getExtras()).getString("key");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("character").child(mKey);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Product charater = dataSnapshot.getValue(Product.class);

                if (charater.getDescripcion() != null) {
                    mdescripcionEditText.setText(charater.getDescripcion());
                }

                if (charater.getCategoria()!= null) {
                    mcategoriaEditText.setText(charater.getCategoria());
                }

                if (charater.getPrecio() != null) {
                    mprecioEditText.setText(String.valueOf(charater.getPrecio()));
                }

                mstockEditText.setText(String.valueOf(charater.getStock()));


                mUrlEditText.setText(String.valueOf(charater.getUrl()));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(EditActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mCharacterButton.setOnClickListener(v -> {
            mDatabaseReference.child("descripcion").setValue(mdescripcionEditText.getText().toString());
            mDatabaseReference.child("categoria").setValue(mcategoriaEditText.getText().toString());
            mDatabaseReference.child("precio").setValue(Double.parseDouble(mprecioEditText.getText().toString()));
            mDatabaseReference.child("stock").setValue(Integer.parseInt(mstockEditText.getText().toString()));
            mDatabaseReference.child("url").setValue(Integer.parseInt(mUrlEditText.getText().toString()));

        });
    }
}