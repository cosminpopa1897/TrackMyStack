package com.trackmystack.popacosmin.trackmystack.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.Models.City;
import com.trackmystack.popacosmin.trackmystack.R;


import java.util.ArrayList;

import static com.trackmystack.popacosmin.trackmystack.R.id.product_list_item;

/**
 * Created by Cosmin on 05-May-18.
 */

public class CityAdapter extends ArrayAdapter<City> {
    private ArrayList<City> cityList;
    private Context mContext;


    private static class CityModel{
        TextView cityNameTextView;
    }

    public CityAdapter(Context context, ArrayList<City> cityList){
        super(context, R.layout.product_listview, cityList);
        this.cityList = cityList;
        City placeHolderCity = new City();
        placeHolderCity.setName("Select a city");
        placeHolderCity.setId(0);
        this.cityList.add(0, placeHolderCity);
        this.mContext = context;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
       View view = getView(position, convertView, parent);
        try{        CityModel cityModel =(CityModel) view.getTag();
        if (position == 0) {
            // Set the hint text color gray
            cityModel.cityNameTextView.setTextColor(Color.GRAY);
        }
        } catch (Exception e){

        }

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        if (position == 0) {
            // Disable the first item from Spinner
            // First item will be use for hint
            return false;
        } else {
            return true;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        City city = getItem(position);
        CityModel cityModel;
        final View result;
        cityModel = new CityModel();
        if(convertView == null){

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.product_listview, parent, false);
            cityModel.cityNameTextView = (TextView) convertView.findViewById(product_list_item);
            result = convertView;
        }
        else{
            cityModel = (CityModel) convertView.getTag();
            result = convertView;
        }

        cityModel.cityNameTextView.setText(city.getName());
        cityModel.cityNameTextView.setTag(position);
        convertView.setTag(cityModel);
        return convertView;
    }



}
