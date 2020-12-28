package com.example.firebasecrud;

import androidx.annotation.Nullable;
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

    @BindView(R.id.stockEditText2)
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

                Product character = dataSnapshot.getValue(Product.class);

                if (character.getDescripcion() != null) {
                    mdescripcionEditText.setText(character.getDescripcion());
                }

                if (character.getCategoria()!= null) {
                    mcategoriaEditText.setText(character.getCategoria());
                }

                if (character.getPrecio() != null) {
                    mprecioEditText.setText(String.valueOf(character.getPrecio()));
                }

                mstockEditText.setText(String.valueOf(character.getStock()));


                mUrlEditText.setText(String.valueOf(character.getUrl()));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(EditActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mCharacterButton.setOnClickListener(v -> {
            mDatabaseReference.child("descripcion").setValue(mdescripcionEditText.getText().toString());
            mDatabaseReference.child("categoria").setValue(mcategoriaEditText.getText().toString());
            mDatabaseReference.child("precio").setValue(Float.valueOf(mprecioEditText.getText().toString()));
            mDatabaseReference.child("stock").setValue(Integer.parseInt(mstockEditText.getText().toString()));
            mDatabaseReference.child("url").setValue(Integer.parseInt(mUrlEditText.getText().toString()));
            finish();
        });
    }
}