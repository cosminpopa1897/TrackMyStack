package com.trackmystack.popacosmin.trackmystack.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.Models.StockItem;
import com.trackmystack.popacosmin.trackmystack.R;

import java.util.ArrayList;

/**
 * Created by Cosmin on 03-May-18.
 */

public class StockItemAdapter extends ArrayAdapter<StockItem> implements View.OnClickListener{

    private ArrayList<StockItem> stockItems;
    private Context mContext;

    public StockItemAdapter(ArrayList<StockItem> stockItems, Context context){
        super(context, R.layout.stockitem_listview_item, stockItems);
        this.stockItems = stockItems;
        this.mContext = context;
    }

    private static class StockItemModel{
        TextView productNameTextView;
    }

    @Override
    public void onClick(View v){

    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        StockItem stockItem = getItem(position);
        StockItemAdapter.StockItemModel stockItemModel;

        final View result;
        if(view == null){
            stockItemModel = new StockItemAdapter.StockItemModel();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.stockitem_listview_item, parent, false);
            stockItemModel.productNameTextView = (TextView) view.findViewById(R.id.stockitem_list_item_productName);
            result = view;
            view.setTag(stockItemModel);
        }
        else{
            stockItemModel = (StockItemAdapter.StockItemModel)view.getTag();
            result = view;
        }
        stockItemModel.productNameTextView.setText(stockItem.Product.Name);
        stockItemModel.productNameTextView.setOnClickListener(this);
        stockItemModel.productNameTextView.setTag(position);

        return result;
    }


}
