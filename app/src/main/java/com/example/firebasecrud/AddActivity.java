package com.example.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity {

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
    Button mproductButton;

    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("character");

        mproductButton.setOnClickListener(v -> {
            String descripcion = mdescripcionEditText.getText().toString();
            String categoria = mcategoriaEditText.getText().toString();
            Float precio = Float.parseFloat(mprecioEditText.getText().toString());
            int stock = Integer.parseInt(mstockEditText.getText().toString());
            int url = Integer.parseInt(mUrlEditText.getText().toString());

                Product mProduct = new Product(descripcion, categoria, precio, stock, url);
                String id = mDatabaseReference.push().getKey();
                if (id != null) { mDatabaseReference.child(id).setValue(mProduct); }

                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
        });

    }

    private void toastModicado(String mensaje){
        Toast.makeText(this, mensaje , Toast.LENGTH_SHORT).show();
    }
}