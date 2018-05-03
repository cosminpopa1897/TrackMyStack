package com.trackmystack.popacosmin.trackmystack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.trackmystack.popacosmin.trackmystack.Adapters.ProductAdapter;
import com.trackmystack.popacosmin.trackmystack.Helpers.Constants;
import com.trackmystack.popacosmin.trackmystack.Helpers.PreferenceFilesRepository;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cosmin on 09-Dec-17.
 */

public class ProductIndexActivity extends BaseActivity {
    public ListView ProductListView;
    public ArrayList<Product> ProductList;
    public PreferenceFilesRepository<ArrayList<Product>> PreferenceFileRepository;
    public HashMap<Integer, Integer> itemIdentityDictionary;
    public SqLiteHelper SqLiteHelper;

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_index_products);
        this.initializeAttributtes();
        Intent intent = getIntent();
        if (intent.hasExtra("name")) {
            Product newProduct = getNewProductFromIntentBundle(intent);
            if (ProductList == null)
                ProductList = new ArrayList<Product>();
            ProductList.add(newProduct);
        }
        if (ProductList == null)
            return;


        ArrayAdapter adapter = new ProductAdapter(this.ProductList, this);
        ProductListView.setAdapter(adapter);
    }

    @Override
    public void onStop() {

        this.PreferenceFileRepository.saveObject(
                getPreferences(Context.MODE_PRIVATE),
                this.ProductList, new TypeToken<ArrayList<Product>>() {}.getType(),
                Constants.PreferenceFilesKeys.ProductList);
        super.onStop();
    }

    @Override
    public void setCurrentMenuButtonId(){
        this.currentMenuButtonId = R.id.index_products;
    }

    private Product getNewProductFromIntentBundle(Intent intent) {
        Bundle bundle = intent.getExtras();
        Product newProduct = Product.UnpackProduct(bundle);
        return newProduct;
    }

    private void initializeAttributtes(){
        setContentView(R.layout.activity_index_products);
        this.ProductListView = (ListView) findViewById(R.id.productList);
        this.PreferenceFileRepository = new PreferenceFilesRepository<ArrayList<Product>>();
        Type objectType =  new TypeToken<ArrayList<Product>>(){}.getType();
        this.SqLiteHelper = com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper.getSqLiteHelperInstance(this);
        this.ProductList = this.SqLiteHelper.getAllProducts();
        this.itemIdentityDictionary = new HashMap<Integer, Integer>();

    }
}
