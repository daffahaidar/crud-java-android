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

public class MainActivity extends AppCompatActivity {


    private EditText productNameEdt, productCategoryEdt, productPriceEdt, productDescriptionEdt;
    private Button addProductBtn, readProductBtn;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productNameEdt = findViewById(R.id.idEdtProductName);
        productCategoryEdt = findViewById(R.id.idEdtProductCategory);
        productPriceEdt = findViewById(R.id.idEdtProductPrice);
        productDescriptionEdt = findViewById(R.id.idEdtProductDescription);
        addProductBtn = findViewById(R.id.idBtnAddProduct);
        readProductBtn = findViewById(R.id.idBtnReadProduct);

        database = new Database(MainActivity.this);

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String productName = productNameEdt.getText().toString();
                String productCategory = productCategoryEdt.getText().toString();
                String productPrice = productPriceEdt.getText().toString();
                String productDescription = productDescriptionEdt.getText().toString();

                if (productName.isEmpty() && productCategory.isEmpty() && productPrice.isEmpty() && productDescription.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }


                database.addNewProduct(productName, productPrice, productDescription, productCategory);

                Toast.makeText(MainActivity.this, "Product has been added.", Toast.LENGTH_SHORT).show();
                productNameEdt.setText("");
                productPriceEdt.setText("");
                productCategoryEdt.setText("");
                productDescriptionEdt.setText("");
            }
        });

        readProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewProduct.class);
                startActivity(i);
            }
        });
    }
}

