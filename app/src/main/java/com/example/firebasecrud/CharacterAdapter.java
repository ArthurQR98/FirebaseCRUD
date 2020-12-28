
package com.example.firebasecrud;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CharacterAdapter extends RecyclerView.Adapter<ViewHolder>{


    private final List<Product> mProductList;

    public CharacterAdapter(List<Product> productList) {
        mProductList = productList;
    }

    @Override
    public void onBindViewHolder(com.example.firebasecrud.ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public com.example.firebasecrud.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.character_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mProductList != null & mProductList.size() > 0) {
            return mProductList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<Product> productList) {
        mProductList.addAll(productList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        if (mProductList != null & mProductList.size() > 0) {
            mProductList.remove(position);
        }
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends com.example.firebasecrud.ViewHolder {

        @BindView(R.id.characterImageView)
        ImageView mCharacterImageView;

        @BindView(R.id.descripcionTextView)
        TextView mdescripcionTextView;

        @BindView(R.id.categoriaTextView)
        TextView mcategoriaTextView;

        @BindView(R.id.precioTextView)
        TextView mprecioTextView;

        @BindView(R.id.stockTextView)
        TextView mstockTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            mCharacterImageView.setImageDrawable(null);
            mdescripcionTextView.setText("");
            mcategoriaTextView.setText("");
            mprecioTextView.setText("");
            mstockTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            Product mProduct = mProductList.get(position);

            int[] charactersImages= {R.drawable.cocacola,R.drawable.comida,R.drawable.maquillaje,R.drawable.terno};

            if (mProduct.getUrl() < 4) {
                mCharacterImageView.setImageResource(charactersImages[mProduct.getUrl()]);
            }

            if (mProduct.getDescripcion() != null) {
                mdescripcionTextView.setText(mProduct.getDescripcion());
            }

            if (mProduct.getCategoria() != null) {
                mcategoriaTextView.setText(mProduct.getCategoria());
            }
            if (mProduct.getPrecio() != null) {
                mprecioTextView.setText(String.valueOf(mProduct.getPrecio()));
            }

            mstockTextView.setText(String.valueOf(mProduct.getStock()));

            itemView.setOnClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("key",  mProduct.getKey());
                itemView.getContext().startActivity(intent);
            });

            itemView.setOnLongClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), EditActivity.class);
                intent.putExtra("key",  mProduct.getKey());
                itemView.getContext().startActivity(intent);
                return false;
            });

        }
    }

}