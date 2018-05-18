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

import com.trackmystack.popacosmin.trackmystack.Helpers.BundleHelpers;
import com.trackmystack.popacosmin.trackmystack.Helpers.Constants;
import com.trackmystack.popacosmin.trackmystack.Helpers.IdentityGenerator;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
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
    private SqLiteHelper sqLiteHelper;
    private boolean isEdit = false;

    @Override
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.initializeAttributes();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle !=null){
        if(bundle.containsKey(Constants.BundleKeys.ProductKey)){
            this.Product = BundleHelpers.UnPack(bundle, Constants.BundleKeys.ProductKey, this.Product.getClass());
            initializeFormValues();
            this.isEdit = true;
        }
        }

        if(this.isEdit){

        }
        this.SaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNewProductToIndex();
            }
        });
    }

    private void sendNewProductToIndex(){
        this.Product = this.getProductForm();
        if(isEdit){
            this.sqLiteHelper.updateProduct(this.Product);
        }
        else
            this.sqLiteHelper.insertProduct(this.Product);
        Intent intent = new Intent(CreateProductActivity.this, ProductIndexActivity.class);
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
        this.sqLiteHelper = SqLiteHelper.getSqLiteHelperInstance(this);
        this.Product = new Product();
    }

    private void initializeFormValues(){
        this.ProductNameBox.setText(this.Product.Name);
        this.ProductDescriptionBox.setText(this.Product.Description);
        this.ProductPriceBox.setText(Float.toString(this.Product.Price));
    }



   @Override
   public void setCurrentMenuButtonId(){
       this.currentMenuButtonId = R.id.edit_product;
   };
}
