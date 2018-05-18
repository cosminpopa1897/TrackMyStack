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
import com.trackmystack.popacosmin.trackmystack.Helpers.BundleHelpers;
import com.trackmystack.popacosmin.trackmystack.Helpers.Constants;
import com.trackmystack.popacosmin.trackmystack.Helpers.FirebaseHelper;
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
    public Button ShopSubmitButon;
    private Spinner citySpinner;
    private Spinner countrySpinner;
    public Shop shop;
    private SqLiteHelper sqLiteHelper;
    private ArrayList<City> cityList;
    private ArrayList<Country> countryList;
    private FirebaseHelper firebaseHelper;
    private boolean isEdit = false;

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
        CountryAdapter countryAdapter = new CountryAdapter(this, this.countryList);
        this.countrySpinner.setAdapter(countryAdapter);
        this.countrySpinner.setPrompt("Select a county");
        this.countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country county = (Country) parent.getSelectedItem();
                if(county == null){
                    return ;
                }
                shop.Country = county.getName();
                cityList = firebaseHelper.cityRepository.getCitiesByCountyId(county.getId());

                CityAdapter cityAdapter = new CityAdapter(parent.getContext(), cityList);
                citySpinner.setAdapter(cityAdapter);
                citySpinner.setPrompt("Select a city");
                citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        City city  = (City) parent.getSelectedItem();
                        shop.City = city.getName();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            if (bundle.containsKey(Constants.BundleKeys.ShopKey)){
                this.shop = BundleHelpers.UnPack(bundle, Constants.BundleKeys.ShopKey, this.shop.getClass());
                this.isEdit = true;
                initializeForm();
            }
        }
    }

    @Override
    public void setCurrentMenuButtonId() {
        this.currentMenuButtonId = R.id.edit_shops;
    }

    private void initializeAttributes(){
        this.ShopNameEdit = (EditText) findViewById(R.id.editShopName);
        this.ShopSubmitButon = (Button) findViewById(R.id.shopSubmitButton);
        this.citySpinner = (Spinner) findViewById(R.id.edit_shop_spinner_city);
        this.countrySpinner = (Spinner) findViewById(R.id.edit_shop_spinner_Country);
        this.cityList = this.generateCityList();
        this.sqLiteHelper = SqLiteHelper.getSqLiteHelperInstance(this);
        this.shop = new Shop();
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.countryList = this.firebaseHelper.countyRepository.getCounties();
    }
    private void getShopForm(Shop newShop) {
        newShop.Name = this.ShopNameEdit.getText().toString();
    }

     private ArrayList<City> generateCityList(){
        ArrayList<City> cityArrayList = new ArrayList<City>();
        for(int i = 0; i < 30; i++){
            City city = new City();
            city.setId(i);
            city.setName("PLoiesti");
            cityArrayList.add(city);
        }
        return cityArrayList;
    }

    private void SendNewShopToIndex(){
        getShopForm(this.shop);
        if(this.isEdit){
            this.sqLiteHelper.updateShop(this.shop);
        }
        else
            this.sqLiteHelper.insertShop(this.shop);
        Intent intent = new Intent(CreateShopActivity.this, ShopsActivity.class);
        startActivity(intent);
    }

    private void initializeForm(){
        this.ShopNameEdit.setText(this.shop.Name);
     //   int countyPosition = getCountyPostion(this.shop.Country);
     //   this.countrySpinner.setSelection(countyPosition);
     //   int cityPosition = getCityPosition(this.shop.City);
     //   this.citySpinner.setSelection(cityPosition);
    }


    private int getCountyPostion(String name){
        for(int i = 0; i< countryList.size(); i++){
            if (countryList.get(i).getName() == name)
                return i;
        }
        return -1;
    }

    private int getCityPosition(String name){
        for(int i = 0; i< cityList.size(); i++){
            if (cityList.get(i).getName() == name)
                return i;
        }
        return -1;
    }


}
