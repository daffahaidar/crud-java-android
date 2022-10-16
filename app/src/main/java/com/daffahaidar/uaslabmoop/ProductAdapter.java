// Nama: Daffa Haidar Nabil Zufar
// NIM: 2440100456

package com.daffahaidar.uaslabmoop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<ProductModal> productModalArrayList;
    private Context context;


    public ProductAdapter(ArrayList<ProductModal> productModalArrayList, Context context) {
        this.productModalArrayList = productModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductModal modal = productModalArrayList.get(position);
        holder.productNameTV.setText(modal.getProductName());
        holder.productDescTV.setText(modal.getProductDescription());
        holder.productPriceTV.setText(modal.getProductPrice());
        holder.productCategoryTV.setText(modal.getProductCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, ProductActivity.class);

                i.putExtra("name", modal.getProductName());
                i.putExtra("description", modal.getProductDescription());
                i.putExtra("price", modal.getProductPrice());
                i.putExtra("category", modal.getProductCategory());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView productNameTV, productDescTV, productPriceTV, productCategoryTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTV = itemView.findViewById(R.id.idTVProductName);
            productDescTV = itemView.findViewById(R.id.idTVProductDescription);
            productPriceTV = itemView.findViewById(R.id.idTVProductPrice);
            productCategoryTV = itemView.findViewById(R.id.idTVProductCategory);
        }
    }
}


