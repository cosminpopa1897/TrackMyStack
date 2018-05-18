package com.trackmystack.popacosmin.trackmystack.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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



    private static class CountryModel {
        TextView countryNameTextView;
    }

    public CountryAdapter(Context context, ArrayList<Country> cityList){
        super(context, R.layout.product_listview, cityList);
        this.CountryList = cityList;
        Country placeHolderCountry = new Country();
        placeHolderCountry.setName( "Select a Country");
        placeHolderCountry.setId( 0);
        this.CountryList.add(0, placeHolderCountry);
        this.mContext = context;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        View view = getView(position, convertView, parent);
        try{
        CountryModel countryModel =(CountryModel) view.getTag();
        if (position == 0) {
            // Set the hint text color gray
            countryModel.countryNameTextView.setTextColor(Color.GRAY);
        }
        }
        catch (Exception e){

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

        Country city = getItem(position);
        CountryModel countryModel;
        final View result;
        countryModel = new CountryModel();
        if(convertView == null){

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.product_listview, parent, false);
            countryModel.countryNameTextView = (TextView) convertView.findViewById(product_list_item);
            result = convertView;
        }
        else{
            countryModel.countryNameTextView = (TextView) convertView.findViewById(product_list_item);
            result = convertView;
        }

        countryModel.countryNameTextView.setText(city.getName());
        countryModel.countryNameTextView.setTag(position);
        return convertView;
    }

}
