package com.trackmystack.popacosmin.trackmystack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.trackmystack.popacosmin.trackmystack.Adapters.ShopAdapter;
import com.trackmystack.popacosmin.trackmystack.Helpers.Constants;
import com.trackmystack.popacosmin.trackmystack.Helpers.PreferenceFilesRepository;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.Product;
import com.trackmystack.popacosmin.trackmystack.Models.Shop;
import com.trackmystack.popacosmin.trackmystack.Navigation.Navigator;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Cosmin on 08-Dec-17.
 */

public class ShopsActivity extends BaseActivity {

    public ListView ShopListView;
    public ArrayList<Shop> ShopList;
    public PreferenceFilesRepository<ArrayList<Shop>> PreferenceFileRepository;
    private SqLiteHelper sqLiteHelper;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        this.intilizeAttributes();
        Intent intent = getIntent();
        if (intent.hasExtra("shop")){
            Shop newShop = getNewShopFromBundle(intent);
            if (this.ShopList == null){
                this.ShopList = new ArrayList<Shop>();
            }
            this.ShopList.add(newShop);
        }

        if(this.ShopList == null){
            return;
        }

        ShopAdapter adapter = new ShopAdapter(this.ShopList, this);
        this.ShopListView.setAdapter(adapter);
    }

    @Override
    public void setCurrentMenuButtonId() {
        this.currentMenuButtonId =  R.id.shops;
    }

    @Override
    public void onStop(){
        this.PreferenceFileRepository.saveObject(
                getPreferences(Context.MODE_PRIVATE),
                this.ShopList, new TypeToken<ArrayList<Shop>>() {}.getType(),
                Constants.PreferenceFilesKeys.ShopList);
        super.onStop();
    }

    private void intilizeAttributes(){
        this.ShopListView = (ListView) findViewById(R.id.shopList);
        this.PreferenceFileRepository = new PreferenceFilesRepository<ArrayList<Shop>>();
        Type objectType =  new TypeToken<ArrayList<Shop>>(){}.getType();
        this.sqLiteHelper = SqLiteHelper.getSqLiteHelperInstance(this);
        this.ShopList = sqLiteHelper.getAllShops();
    }

    private Shop getNewShopFromBundle(Intent intent){
        Bundle bundle = intent.getExtras();
        return Shop.unBundleShop(bundle);
    }

}
