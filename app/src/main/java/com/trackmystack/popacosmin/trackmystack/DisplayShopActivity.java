package com.trackmystack.popacosmin.trackmystack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.Helpers.Constants;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.Shop;

/**
 * Created by Cosmin on 01-May-18.
 */

public class DisplayShopActivity extends BaseActivity {

    private Shop shop;
    private TextView shopNameTextView;
    private TextView cityTextView;
    private TextView countryTextView;
    private Button displayStocksButton;


    @Override
    public void onCreate(Bundle SavedIntanceState){
        super.onCreate(SavedIntanceState);
        setContentView(R.layout.activity_display_shop);
        intializeAttributes();
        mapShopToDisplay();
        this.displayStocksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayShopActivity.this, DisplayStockItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.BundleKeys.ShopId, shop.Id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    public void setCurrentMenuButtonId() {
        this.currentMenuButtonId = R.id.shops;
    }

    private void intializeAttributes(){
        this.shopNameTextView = (TextView) findViewById(R.id.display_shop_textView_shopName);
        this.cityTextView = (TextView) findViewById(R.id.display_shop_textView_city);
        this.countryTextView = (TextView) findViewById(R.id.display_shop_textView_country);
        this.displayStocksButton = (Button) findViewById(R.id.display_shop_button_displayStocks);
        this.displayStocksButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.shop = Shop.unBundleShop(bundle);
    }

    private void mapShopToDisplay(){
        this.shopNameTextView.setText(this.shop.Name);
        this.countryTextView.setText(this.shop.Country);
        this.cityTextView.setText(this.shop.City);
    }

    private void sendShopIdToDisplayStockActivity(){
        Intent intent = new Intent(DisplayShopActivity.this, DisplayStockItemActivity.class);
        intent.putExtra(Constants.BundleKeys.ShopId, this.shop.Id);
        startActivity(intent);
    }


}
