package com.trackmystack.popacosmin.trackmystack.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.trackmystack.popacosmin.trackmystack.DisplayTransactionActivity;
import com.trackmystack.popacosmin.trackmystack.MainActivity;
import com.trackmystack.popacosmin.trackmystack.Models.Transaction;
import com.trackmystack.popacosmin.trackmystack.ProductIndexActivity;
import com.trackmystack.popacosmin.trackmystack.R;
import com.trackmystack.popacosmin.trackmystack.TransactionActivity;

import java.util.ArrayList;

/**
 * Created by Cosmin on 30-Apr-18.
 */

public class TransactionAdapter extends ArrayAdapter<Transaction> implements View.OnClickListener {


    private ArrayList<Transaction> transactions;
    Context mContext;

    private static class ViewHolder{
        TextView transactionName;
    }

    public TransactionAdapter(ArrayList<Transaction> transactions, Context context){
        super(context, R.layout.product_listview, transactions);
        this.transactions = transactions;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {

        int postion = (int)v.getTag();
        Object object = getItem(postion);
        Transaction transaction = (Transaction) object;
        Bundle transactionBundle = Transaction.BundleTransaction(transaction);
        Intent intent = new Intent(mContext, DisplayTransactionActivity.class);
        intent.putExtras(transactionBundle);
        mContext.startActivity(intent);
    }
    @Override
    public View getView(int position, View view, ViewGroup parent){
        Transaction transaction = getItem(position);
        ViewHolder viewHolder;

        final View result;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.product_listview, parent, false);
            viewHolder.transactionName = (TextView) view.findViewById(R.id.product_list_item);
            result = view;
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
            result = view;
        }
        viewHolder.transactionName.setText(transaction.DateReceived.toString());
        viewHolder.transactionName.setOnClickListener(this);
        viewHolder.transactionName.setTag(position);
        return view;
    }
}
