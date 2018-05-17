package com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.trackmystack.popacosmin.trackmystack.Models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cosmin on 01-May-18.
 */

public class ProductScripts {

      static class ProductColumns {
        public static final String idCol = "id";
        public static final String nameCol = "name";
        public static final String descriptionCol = "Description";
        public static final String priceCol = "Price";
        public static final String isDeletedColumn = "IsDeleted";

    }

    static class ProductColumnAttributes {
        public static final String idAttributes =" INTEGER NOT NULL PRIMARY KEY";
        public static final String nameAttributes = "TEXT NOT NULL";
        public static final String descriptionAttributes = "TEXT" ;
        public static final String priceAttribute = "REAL NOT NULL DEFAULT 0";
        public static final String isDeletedAttribUte = "NUMERIC NOT NULL DEFAULT 0";
    }

    public static String productTable = "Product";


    private static final String createTable = "CREATE TABLE " + productTable +"("
            + ProductColumns.idCol + " " +ProductColumnAttributes.idAttributes + ","
            + ProductColumns.nameCol + " " + ProductColumnAttributes.nameAttributes + ","
            + ProductColumns.descriptionCol + " " + ProductColumnAttributes.descriptionAttributes + ","
            + ProductColumns.priceCol + " " + ProductColumnAttributes.priceAttribute + ","
            + ProductColumns.isDeletedColumn + " " + ProductColumnAttributes.isDeletedAttribUte + ")";


    public static String getCreateScripts() {
        return createTable;
    }

    public static String getUpdateScripts() {
        return null;
    }


    public static boolean insertProduct(SQLiteDatabase db, Product product){
        ContentValues contentValues = getProductContentValues(product);
        long result = db.insert(productTable, null, contentValues);
        if (result == -1)
            return false;
        return true;
    }

    public static boolean updateProduct(SQLiteDatabase db, Product product){
        ContentValues contentValues = getProductContentValues(product);
        long result = db.update(productTable, contentValues, "_id="+product.Id, null);
        if (result == -1)
            return false;
        return true;
    }

    private static ContentValues getProductContentValues(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductColumns.nameCol, product.Name);
        contentValues.put(ProductColumns.descriptionCol, product.Description);
        contentValues.put(ProductColumns.priceCol, product.Price);
        contentValues.put(ProductColumns.descriptionCol, product.Description);
        return contentValues;
    }

    public static List<Product> getAllProducts(SQLiteDatabase db){
        String selectQuery = "SELECT * FROM " + productTable;
        List<Product> productList =new ArrayList<Product>();


        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Product product =new Product();
                product.Id = Integer.parseInt(cursor.getString(0));
                product.Name =cursor.getString(1);
                product.Description =cursor.getString(2);
                product.Price = Float.parseFloat(cursor.getString(3));
                product.IsDeleted = Boolean.parseBoolean(cursor.getString(4));
                productList.add(product);
            }
            while(cursor.moveToNext());
        }

        return productList;
    }

    public static Product getProduct(SQLiteDatabase db, int id){
        String query = "SELECT * FROM " + productTable
                +" WHERE " + ProductColumns.idCol + "=" + Integer.toString(id);
        Cursor cursor = db.rawQuery(query, null);
        Product product =  new Product();

        if(cursor.moveToFirst()){
            do{
                product.Id = Integer.parseInt(cursor.getString(0));
                product.Name =cursor.getString(1);
                product.Description =cursor.getString(2);
                product.Price = Float.parseFloat(cursor.getString(3));
                product.IsDeleted = Boolean.parseBoolean(cursor.getString(4));
            }
            while(cursor.moveToNext());
        }
        return product;
    }

}
