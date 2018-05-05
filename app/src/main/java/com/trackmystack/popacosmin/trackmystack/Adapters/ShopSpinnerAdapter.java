package com.trackmystack.popacosmin.trackmystack.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.Models.Shop;
import com.trackmystack.popacosmin.trackmystack.R;

import java.util.ArrayList;

/**
 * Created by Cosmin on 05-May-18.
 */

public class ShopSpinnerAdapter extends ArrayAdapter<Shop> {

    private ArrayList<Shop> shopList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public ShopSpinnerAdapter(Context mContext, ArrayList<Shop> shopList, View.OnClickListener onClickListener){
        super(mContext, R.layout.shop_spinner_item, shopList);
        this.shopList = shopList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return this.shopList.size();
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getView(position, convertView, parent);
    }
    private static class ShopSpinnerModel{
        TextView shopNameTextView;
        TextView cityTextView;
        TextView countryTextview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ShopSpinnerModel shopModel = new ShopSpinnerModel();
        Shop shop = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(this.mContext);
            convertView = inflater.inflate(R.layout.shop_spinner_item, parent, false);
            shopModel.shopNameTextView = (TextView) convertView.findViewById(R.id.spinner_item_shop_name);
            shopModel.cityTextView = (TextView) convertView.findViewById(R.id.spinner_item_shop_city);
            shopModel.countryTextview = (TextView) convertView.findViewById(R.id.spinner_item_shop_country);
        }
        else{
            shopModel = (ShopSpinnerModel) convertView.getTag();
        }

        shopModel.countryTextview.setText(shop.Country);
        shopModel.shopNameTextView.setText(shop.Name);
        shopModel.cityTextView.setText(shop.City);
        convertView.setTag(shopModel);
        return convertView;
    }
}
