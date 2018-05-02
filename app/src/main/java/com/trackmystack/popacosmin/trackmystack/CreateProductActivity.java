package com.trackmystack.popacosmin.trackmystack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.trackmystack.popacosmin.trackmystack.Helpers.IdentityGenerator;
import com.trackmystack.popacosmin.trackmystack.Models.Product;

import com.trackmystack.popacosmin.trackmystack.Navigation.Navigator;

/**
 * Created by Cosmin on 07-Dec-17.
 */

public class CreateProductActivity extends BaseActivity {
    public Button SaveProduct;
    public Product Product;
    public EditText ProductNameBox;
    public EditText ProductDescriptionBox;
    public EditText ProductPriceBox;

    @Override
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.initializeAttributes();
        this.SaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNewProductToIndex();
            }
        });
    }

    private void sendNewProductToIndex(){
        this.Product = this.getProductForm();
        Bundle newState = Product.BundleProduct(this.Product);
        Intent intent = new Intent(CreateProductActivity.this, ProductIndexActivity.class);
        intent.putExtras(newState);
        startActivity(intent);
    }

    private Product getProductForm(){
        Product = new Product();
        Product.Id = IdentityGenerator.getProductId();
        Product.Description = ProductDescriptionBox.getText().toString();
        Product.Name = ProductNameBox.getText().toString();
        Product.Price = Float.parseFloat(ProductPriceBox.getText().toString());
        return Product;
    }

    private Bundle CreateProductBundle(){
        Bundle newState = new Bundle();
        newState.putString("name",Product.Name);
        newState.putString("description", Product.Description);
        newState.putString("id", Integer.toString(Product.Id));
        newState.putString("price", Float.toHexString(Product.Price));
        return newState;
    }

    private void initializeAttributes(){
        setContentView(R.layout.activity_new_product);
        this.SaveProduct =(Button) findViewById(R.id.productSubmitButton);
        this.ProductNameBox = (EditText) findViewById(R.id.editProductName);
        this.ProductDescriptionBox = (EditText) findViewById(R.id.editProductDescription);
        this.ProductPriceBox = (EditText) findViewById(R.id.editProductPrice);
    }

   @Override
   public void setCurrentMenuButtonId(){
       this.currentMenuButtonId = R.id.edit_product;
   };
}
