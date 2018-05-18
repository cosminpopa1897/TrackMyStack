package com.trackmystack.popacosmin.trackmystack.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.Models.Product;
import com.trackmystack.popacosmin.trackmystack.R;

import java.util.ArrayList;

/**
 * Created by Cosmin on 05-May-18.
 */

public class ProductSpinnerAdapter extends ArrayAdapter<Product> {

    private ArrayList<Product> productList;
    private Context mContext;


    public ProductSpinnerAdapter(Context context, ArrayList<Product> productList) {
        super(context, R.layout.product_spinner_item, productList);
        this.productList = productList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return this.productList.size();
    }
    private static class ProductSpinnerModel{
        TextView productNameTextView;
        TextView priceTextView;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Product product = getItem(position);
        ProductSpinnerModel productSpinnerModel;
        if(convertView == null){
            productSpinnerModel = new ProductSpinnerModel();
            LayoutInflater inflater = LayoutInflater.from(this.mContext);
            convertView = inflater.inflate(R.layout.product_spinner_item, parent, false);
            productSpinnerModel.productNameTextView = (TextView) convertView.findViewById(R.id.spinner_item_product_name);
            productSpinnerModel.priceTextView = (TextView) convertView.findViewById(R.id.spinner_item_product_price);
        }
        else{
            productSpinnerModel = (ProductSpinnerModel) convertView.getTag();
        }

        productSpinnerModel.productNameTextView.setText(product.Name);
        productSpinnerModel.priceTextView.setText(Float.toString(product.Price));
        convertView.setTag(productSpinnerModel);


        return convertView;
    }

}
