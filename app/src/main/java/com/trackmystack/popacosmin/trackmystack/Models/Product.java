package com.trackmystack.popacosmin.trackmystack.Models;

import android.os.Bundle;

import com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts.ProductScripts;

/**
 * Created by Cosmin on 08-Dec-17.
 */

public class Product {
    public int Id;
    public String Name;
    public String Description;
    public float Price;
    public boolean IsDeleted ;

    public static Bundle BundleProduct(Product product){
        Bundle newState = new Bundle();
        newState.putString("name",product.Name);
        newState.putString("description", product.Description);
        newState.putString("id", Integer.toString(product.Id));
        newState.putString("price", Float.toHexString(product.Price));
        return newState;
    }

    public static Product UnpackProduct(Bundle productBundle){
        Product newProduct = new Product();
        newProduct.Name = productBundle.get("name").toString();
        newProduct.Description = productBundle.get("description").toString();
        newProduct.IsDeleted = false;
        newProduct.Price = Float.parseFloat(productBundle.get("price").toString());
        newProduct.Id = Integer.parseInt(productBundle.get("id").toString());
        return newProduct;
    }
}
