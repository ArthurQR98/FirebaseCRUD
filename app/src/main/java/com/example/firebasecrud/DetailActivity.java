package com.example.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.lang.Double.valueOf;

public class DetailActivity extends AppCompatActivity {
    // es lo que tendra el activity detail, no va todos los datos solo nombre y descripcion
    @BindView(R.id.characterImageView)
    ImageView mCharacterImageView;

    @BindView(R.id.backgroundImageView)
    ImageView mBackgroundImageView;

    @BindView(R.id.descripcionTextView2)
    TextView mdescripcionTextView;

    @BindView(R.id.descriptionTextView)
    TextView mDescriptionTextView;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        String mKey= Objects.requireNonNull(getIntent().getExtras()).getString("key");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("character").child(mKey);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Product charater = dataSnapshot.getValue(Product.class);

                if (charater.getDescripcion() != null) {
                    mdescripcionTextView.setText(charater.getDescripcion());
                }


                if (charater.getPrecio() != null) {
                    mDescriptionTextView.setText(String.valueOf(charater.getPrecio()));
                }

                int[] charactersImages= {R.drawable.cocacola,R.drawable.comida,R.drawable.maquillaje,R.drawable.terno};
                int[] backgroundsImages= {R.drawable.cocacola_background,R.drawable.comida_background,R.drawable.maquillaje_background,R.drawable.terno_background};
                if (charater.getUrl() < 4) {
                    mCharacterImageView.setImageResource(charactersImages[charater.getUrl()]);
                }

                if (charater.getUrl() < 4) {
                    mBackgroundImageView.setImageResource(backgroundsImages[charater.getUrl()]);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(DetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}