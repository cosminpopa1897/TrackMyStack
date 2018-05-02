package com.trackmystack.popacosmin.trackmystack.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.DisplayProductActivity;
import com.trackmystack.popacosmin.trackmystack.Models.Product;
import com.trackmystack.popacosmin.trackmystack.Models.Transaction;
import com.trackmystack.popacosmin.trackmystack.ProductIndexActivity;
import com.trackmystack.popacosmin.trackmystack.R;
import com.trackmystack.popacosmin.trackmystack.TransactionActivity;

import java.util.ArrayList;

/**
 * Created by Cosmin on 01-May-18.
 */

public class ProductAdapter extends ArrayAdapter<Product> implements View.OnClickListener{

    private ArrayList<Product> products;
    private Context mContext;

    public ProductAdapter(ArrayList<Product> products, Context mContext){
        super(mContext, R.layout.product_listview, products);
        this.products = products;
        this.mContext = mContext;
    }

    private static class ViewHolder{
        TextView productName;
    }
    @Override
    public void onClick(View v) {

        int postion = (int)v.getTag();
        Object object = getItem(postion);
        Product product = (Product) object;
        Bundle productBundle = Product.BundleProduct(product);
        Intent intent = new Intent(mContext, DisplayProductActivity.class);
        intent.putExtras(productBundle);
        mContext.startActivity(intent);
    }
    @Override
    public View getView(int position, View view, ViewGroup parent){
        Product product = getItem(position);
        ProductAdapter.ViewHolder viewHolder;

        final View result;
        if(view == null){
            viewHolder = new ProductAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.product_listview, parent, false);
            viewHolder.productName = (TextView) view.findViewById(R.id.product_list_item);
            result = view;
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ProductAdapter.ViewHolder) view.getTag();
            result = view;
        }
        viewHolder.productName.setText(product.Name);
        viewHolder.productName.setOnClickListener(this);
        viewHolder.productName.setTag(position);
        return view;
    }

}
