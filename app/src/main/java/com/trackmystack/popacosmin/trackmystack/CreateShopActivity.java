package com.trackmystack.popacosmin.trackmystack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.trackmystack.popacosmin.trackmystack.Helpers.IdentityGenerator;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.Shop;
import com.trackmystack.popacosmin.trackmystack.Models.Transaction;
import com.trackmystack.popacosmin.trackmystack.Navigation.Navigator;

/**
 * Created by Cosmin on 07-Dec-17.
 */

public class CreateShopActivity extends BaseActivity {
    public EditText ShopNameEdit;
    public EditText ShopCityEdit;
    public EditText ShopCountryEdit;
    public Button ShopSubmitButon;
    public Shop shop;
    private SqLiteHelper sqLiteHelper;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop);
        initializeAttributes();
        this.ShopSubmitButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendNewShopToIndex();
            }


        });
    }

    @Override
    public void setCurrentMenuButtonId() {
        this.currentMenuButtonId = R.id.edit_shops;
    }

    private void initializeAttributes(){
        this.ShopNameEdit = (EditText) findViewById(R.id.editShopName);
        this.ShopCityEdit = (EditText) findViewById(R.id.EditShopCity);
        this.ShopCountryEdit = (EditText) findViewById(R.id.EditShopCity);
        this.ShopSubmitButon = (Button) findViewById(R.id.shopSubmitButton);
        this.sqLiteHelper = SqLiteHelper.getSqLiteHelperInstance(this);
    }
    private Shop getShopForm() {
        Shop newShop = new Shop();
        newShop.Id = IdentityGenerator.getProductId();
        newShop.Name = this.ShopNameEdit.getText().toString();
        newShop.City = this.ShopCityEdit.getText().toString();
        newShop.Country = this.ShopCountryEdit.getText().toString();
        return newShop;
    }

    private void SendNewShopToIndex(){
        this.shop = getShopForm();
        this.sqLiteHelper.insertShop(this.shop);
       // Bundle bundle = Shop.bundleShop(this.shop);
        Intent intent = new Intent(CreateShopActivity.this, ShopsActivity.class);
       // intent.putExtras(bundle);
        startActivity(intent);
    }


}
