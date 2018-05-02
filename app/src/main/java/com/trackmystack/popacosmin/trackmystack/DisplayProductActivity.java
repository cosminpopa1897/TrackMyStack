package com.trackmystack.popacosmin.trackmystack;

import android.os.Bundle;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.Models.Product;

/**
 * Created by Cosmin on 01-May-18.
 */

public class DisplayProductActivity extends BaseActivity {

    private Product product;
    private TextView productNameTextView;
    private TextView descriptionTextView;
    private TextView priceTextView;

    @Override
    public void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_display_product);
        intializeAttributes();
        mapProductToDisplay();
    }
    @Override
    public void setCurrentMenuButtonId() {
        this.currentMenuButtonId = R.id.index_products;
    }

    private void intializeAttributes(){
        this.productNameTextView = (TextView) findViewById(R.id.display_product_textView_name);
        this.descriptionTextView = (TextView) findViewById(R.id.display_product_textView_description);
        this.priceTextView = (TextView) findViewById(R.id.display_product_textView_price);
        Bundle bundle = getIntent().getExtras();
        this.product = Product.UnpackProduct(bundle);
    }

    private void mapProductToDisplay(){
        this.productNameTextView.setText(this.product.Name);
        this.descriptionTextView.setText(this.product.Description);
        this.priceTextView.setText(Float.toString(this.product.Price));
    }
}
