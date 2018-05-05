package com.trackmystack.popacosmin.trackmystack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.trackmystack.popacosmin.trackmystack.Adapters.StockItemAdapter;
import com.trackmystack.popacosmin.trackmystack.Helpers.Constants;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.StockItem;

import java.util.ArrayList;

/**
 * Created by Cosmin on 03-May-18.
 */

public class DisplayStockItemActivity extends BaseActivity {

    private ListView stockItemListView;
    private ArrayList<StockItem> stockItems;
    private SqLiteHelper sqLiteHelper;
    private int shopId;

    @Override
    public void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_display_stockitem);
        this.initializeAttributes();
        populateListView();
    }

    @Override
    public void setCurrentMenuButtonId() {
        this.currentMenuButtonId = R.id.index_products;
    }

    private void initializeAttributes(){
        this.stockItemListView = (ListView) findViewById(R.id.stockItemListView);
        this.shopId = getProductIdFromIntent();
        this.sqLiteHelper = SqLiteHelper.getSqLiteHelperInstance(this);
        this.stockItems = this.sqLiteHelper.getStockItemsByShop(this.shopId);
    }

    private int getProductIdFromIntent(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        return (int) bundle.get(Constants.BundleKeys.ShopId);
    }

    private void populateListView(){
        StockItemAdapter stockItemAdapter = new StockItemAdapter(this.stockItems, this);
        this.stockItemListView.setAdapter(stockItemAdapter);
    }
}
