package com.trackmystack.popacosmin.trackmystack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.trackmystack.popacosmin.trackmystack.Adapters.CityAdapter;
import com.trackmystack.popacosmin.trackmystack.Adapters.CountryAdapter;
import com.trackmystack.popacosmin.trackmystack.Helpers.IdentityGenerator;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.City;
import com.trackmystack.popacosmin.trackmystack.Models.Country;
import com.trackmystack.popacosmin.trackmystack.Models.Shop;

import java.util.ArrayList;

/**
 * Created by Cosmin on 07-Dec-17.
 */

public class CreateShopActivity extends BaseActivity {
    public EditText ShopNameEdit;
    public EditText ShopCityEdit;
    public EditText ShopCountryEdit;
    public Button ShopSubmitButon;
    private Spinner citySpinner;
    private Spinner countrySpinner;
    public Shop shop;
    private SqLiteHelper sqLiteHelper;
    private ArrayList<City> cityList;
    private ArrayList<Country> countryList;

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
        CityAdapter cityAdapter = new CityAdapter(this, this.cityList);
        this.citySpinner.setAdapter(cityAdapter);
        this.citySpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                City city = (City) citySpinner.getSelectedItem();
                ArrayList<Country> countries = generCountries();
                CountryAdapter countryAdapter = new CountryAdapter(parent.getContext(), countries);
                countrySpinner.setAdapter(countryAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        this.citySpinner = (Spinner) findViewById(R.id.edit_shop_spinner_city);
        this.countrySpinner = (Spinner) findViewById(R.id.edit_shop_spinner_Country);
        this.cityList = this.generateCityList();
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

    private ArrayList<Country> generCountries(){
        ArrayList<Country> countryArrayList = new ArrayList<Country>();
        for(int i = 0; i < 30; i++){
            Country country = new Country();
            country.Id = i;
            country.Name = "Romania";
            countryArrayList.add(country);
        }
        return countryArrayList;
    }
    private ArrayList<City> generateCityList(){
        ArrayList<City> cityArrayList = new ArrayList<City>();
        for(int i = 0; i < 30; i++){
            City city = new City();
            city.Id = i;
            city.Name = "PLoiesti";
            cityArrayList.add(city);
        }
        return cityArrayList;
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
