package com.trackmystack.popacosmin.trackmystack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.Models.Shop;

/**
 * Created by Cosmin on 01-May-18.
 */

public class DisplayShopActivity extends BaseActivity {

    private Shop shop;
    private TextView shopNameTextView;
    private TextView cityTextView;
    private TextView countryTextView;


    @Override
    public void onCreate(Bundle SavedIntanceState){
        super.onCreate(SavedIntanceState);
        setContentView(R.layout.activity_display_shop);
        intializeAttributes();
        mapShopToDisplay();
    }
    @Override
    public void setCurrentMenuButtonId() {
        this.currentMenuButtonId = R.id.shops;
    }

    private void intializeAttributes(){
        this.shopNameTextView = (TextView) findViewById(R.id.display_shop_textView_shopName);
        this.cityTextView = (TextView) findViewById(R.id.display_shop_textView_city);
        this.countryTextView = (TextView) findViewById(R.id.display_shop_textView_country);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.shop = Shop.unBundleShop(bundle);
    }

    private void mapShopToDisplay(){
        this.shopNameTextView.setText(this.shop.Name);
        this.countryTextView.setText(this.shop.Country);
        this.cityTextView.setText(this.shop.City);
    }


}
