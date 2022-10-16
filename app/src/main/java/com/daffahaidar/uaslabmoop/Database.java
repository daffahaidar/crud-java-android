// Nama: Daffa Haidar Nabil Zufar
// NIM: 2440100456

package com.daffahaidar.uaslabmoop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {


    private static final String DB_NAME = "productdb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "myproducts";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String PRICE_COL = "price";
    private static final String DESCRIPTION_COL = "description";
    private static final String CATEGORY_COL = "category";
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + PRICE_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + CATEGORY_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewProduct(String productName, String productPrice, String productDescription, String productCategory) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL, productName);
        values.put(PRICE_COL, productPrice);
        values.put(DESCRIPTION_COL, productDescription);
        values.put(CATEGORY_COL, productCategory);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<ProductModal> readProducts() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorProducts = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<ProductModal> productModalArrayList = new ArrayList<>();

        if (cursorProducts.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                productModalArrayList.add(new ProductModal(cursorProducts.getString(1),
                        cursorProducts.getString(4),
                        cursorProducts.getString(2),
                        cursorProducts.getString(3)));
            } while (cursorProducts.moveToNext());
        }

        cursorProducts.close();
        return productModalArrayList;
    }

    public void updateProduct(String originalProductName, String productName, String productDescription,
                             String productCategory, String productPrice) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, productName);
        values.put(PRICE_COL, productPrice);
        values.put(DESCRIPTION_COL, productDescription);
        values.put(CATEGORY_COL, productCategory);

        db.update(TABLE_NAME, values, "name=?", new String[]{originalProductName});
        db.close();
    }

    public void deleteProduct(String productName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name=?", new String[]{productName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}




