package com.trackmystack.popacosmin.trackmystack.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.DisplayShopActivity;
import com.trackmystack.popacosmin.trackmystack.Models.Product;
import com.trackmystack.popacosmin.trackmystack.Models.Shop;
import com.trackmystack.popacosmin.trackmystack.Models.Transaction;
import com.trackmystack.popacosmin.trackmystack.R;
import com.trackmystack.popacosmin.trackmystack.TransactionActivity;

import java.util.ArrayList;

/**
 * Created by Cosmin on 01-May-18.
 */

public class ShopAdapter extends ArrayAdapter<Shop> implements View.OnClickListener{

    private ArrayList<Shop> shopsList;
    private Context mContext;

    public ShopAdapter(ArrayList<Shop> shopsList, Context mContext){
        super(mContext, R.layout.product_listview, shopsList);
        this.shopsList = shopsList;
        this.mContext = mContext;
    }

    private static class ViewHolder{
        TextView shopName;
    }
    @Override
    public void onClick(View v) {

        int postion = (int)v.getTag();
        Object object = getItem(postion);
        Shop shop = (Shop) object;
        Bundle productBundle = Shop.bundleShop(shop);
        Intent intent = new Intent(mContext, DisplayShopActivity.class);
        intent.putExtras(productBundle);
        mContext.startActivity(intent);
    }
    @Override
    public View getView(int position, View view, ViewGroup parent){
        Shop shop = getItem(position);
        ShopAdapter.ViewHolder viewHolder;

        final View result;
        if(view == null){
            viewHolder = new ShopAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.product_listview, parent, false);
            viewHolder.shopName = (TextView) view.findViewById(R.id.product_list_item);
            result = view;
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ShopAdapter.ViewHolder) view.getTag();
            result = view;
        }
        viewHolder.shopName.setText(shop.Name);
        viewHolder.shopName.setOnClickListener(this);
        viewHolder.shopName.setTag(position);
        return view;
    }

}
