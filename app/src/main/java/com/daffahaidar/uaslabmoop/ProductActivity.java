// Nama: Daffa Haidar Nabil Zufar
// NIM: 2440100456

package com.daffahaidar.uaslabmoop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    private EditText productNameEdt, productCategoryEdt, productPriceEdt, productDescriptionEdt;
    private Button updateProductBtn, deleteProductBtn;
    private Database database;
    String productName, productDesc, productPrice, productCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productNameEdt = findViewById(R.id.idEdtProductName);
        productCategoryEdt = findViewById(R.id.idEdtProductCategory);
        productPriceEdt = findViewById(R.id.idEdtProductPrice);
        productDescriptionEdt = findViewById(R.id.idEdtProductDescription);
        updateProductBtn = findViewById(R.id.idBtnUpdateProduct);
        deleteProductBtn = findViewById(R.id.idBtnDelete);

        database = new Database(ProductActivity.this);

        productName = getIntent().getStringExtra("name");
        productDesc = getIntent().getStringExtra("description");
        productPrice = getIntent().getStringExtra("price");
        productCategory = getIntent().getStringExtra("category");

        productNameEdt.setText(productName);
        productDescriptionEdt.setText(productDesc);
        productCategoryEdt.setText(productCategory);
        productPriceEdt.setText(productPrice);


        updateProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database.updateProduct(productName, productNameEdt.getText().toString(), productDescriptionEdt.getText().toString(), productCategoryEdt.getText().toString(), productPriceEdt.getText().toString());

                Toast.makeText(ProductActivity.this, "Product Updated..", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(ProductActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        deleteProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteProduct(productName);
                Toast.makeText(ProductActivity.this, "Deleted the product", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ProductActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}

