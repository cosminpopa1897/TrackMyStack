package com.trackmystack.popacosmin.trackmystack.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.Models.City;
import com.trackmystack.popacosmin.trackmystack.Models.Country;
import com.trackmystack.popacosmin.trackmystack.R;

import java.util.ArrayList;

import static com.trackmystack.popacosmin.trackmystack.R.id.product_list_item;

/**
 * Created by Cosmin on 05-May-18.
 */

public class CountryAdapter extends ArrayAdapter<Country> {

    private ArrayList<Country> CountryList;
    private Context mContext;



    private static class CityModel{
        TextView cityNameTextView;
    }

    public CountryAdapter(Context context, ArrayList<Country> cityList){
        super(context, R.layout.product_listview, cityList);
        this.CountryList = cityList;
        this.mContext = context;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Country city = getItem(position);
        CountryAdapter.CityModel cityModel;
        final View result;
        cityModel = new CountryAdapter.CityModel();
        if(convertView == null){

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.product_listview, parent, false);
            cityModel.cityNameTextView = (TextView) convertView.findViewById(product_list_item);
            result = convertView;
        }
        else{
            cityModel.cityNameTextView = (TextView) convertView.findViewById(product_list_item);
            result = convertView;
        }

        cityModel.cityNameTextView.setText(city.Name);
        cityModel.cityNameTextView.setTag(position);
        return convertView;
    }

}
