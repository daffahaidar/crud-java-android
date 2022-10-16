// Nama: Daffa Haidar Nabil Zufar
// NIM: 2440100456

package com.daffahaidar.uaslabmoop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewProduct extends AppCompatActivity {

    private ArrayList<ProductModal> productModalArrayList;
    private Database database;
    private ProductAdapter productAdapter;
    private RecyclerView productsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        productModalArrayList = new ArrayList<>();
        database = new Database(ViewProduct.this);

        productModalArrayList = database.readProducts();

        productAdapter = new ProductAdapter(productModalArrayList, ViewProduct.this);
        productsRV = findViewById(R.id.idRVProduct);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewProduct.this, RecyclerView.VERTICAL, false);
        productsRV.setLayoutManager(linearLayoutManager);

        productsRV.setAdapter(productAdapter);
    }
}
