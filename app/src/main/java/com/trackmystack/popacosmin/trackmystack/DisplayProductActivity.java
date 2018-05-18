package com.trackmystack.popacosmin.trackmystack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.Helpers.BundleHelpers;
import com.trackmystack.popacosmin.trackmystack.Helpers.Constants;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.Product;

/**
 * Created by Cosmin on 01-May-18.
 */

public class DisplayProductActivity extends BaseActivity {

    private Product product;
    private TextView productNameTextView;
    private TextView descriptionTextView;
    private TextView priceTextView;
    private Button editProductButton;
    private Button deleteProductButton;
    private SqLiteHelper sqLiteHelper;

    @Override
    public void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_display_product);
        intializeAttributes();
        mapProductToDisplay();
        this.editProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = BundleHelpers.Pack(product, product.getClass() ,Constants.BundleKeys.ProductKey );
                Intent intent = new Intent(DisplayProductActivity.this, CreateProductActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        this.deleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteHelper.deleteProduct(product);
                Intent intent = new Intent(DisplayProductActivity.this, ProductIndexActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void setCurrentMenuButtonId() {
        this.currentMenuButtonId = R.id.index_products;
    }

    private void intializeAttributes(){
        this.productNameTextView = (TextView) findViewById(R.id.display_product_textView_name);
        this.descriptionTextView = (TextView) findViewById(R.id.display_product_textView_description);
        this.priceTextView = (TextView) findViewById(R.id.display_product_textView_price);
        this.editProductButton = (Button) findViewById(R.id.display_product_button_editProduct);
        this.deleteProductButton = (Button) findViewById(R.id.display_product_button_deleteShop);
        this.sqLiteHelper = SqLiteHelper.getSqLiteHelperInstance(this);
        Bundle bundle = getIntent().getExtras();
        this.product = Product.UnpackProduct(bundle);
    }

    private void mapProductToDisplay(){
        this.productNameTextView.setText(this.product.Name);
        this.descriptionTextView.setText(this.product.Description);
        this.priceTextView.setText(Float.toString(this.product.Price));
    }
}
