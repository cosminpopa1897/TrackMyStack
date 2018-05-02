package com.trackmystack.popacosmin.trackmystack.Navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.trackmystack.popacosmin.trackmystack.CreateProductActivity;
import com.trackmystack.popacosmin.trackmystack.CreateShopActivity;
import com.trackmystack.popacosmin.trackmystack.MainActivity;
import com.trackmystack.popacosmin.trackmystack.ProductIndexActivity;
import com.trackmystack.popacosmin.trackmystack.R;
import com.trackmystack.popacosmin.trackmystack.ShopsActivity;
import com.trackmystack.popacosmin.trackmystack.TransactionActivity;



/**
 * Created by Cosmin on 08-Dec-17.
 */

public class Navigator {

    public void MapActivities(Context packageContext, MenuItem menuItem){

        switch (menuItem.getItemId()){
            case R.id.new_transaction:{
                NavigateToActivity(packageContext, TransactionActivity.class);
            }
            break;
            case R.id.view_transactions:{
                NavigateToActivity(packageContext, MainActivity.class);
            }
            break;
            case R.id.edit_shops:{
                NavigateToActivity(packageContext, CreateShopActivity.class);
            }
            break;
            case R.id.shops:{
                NavigateToActivity(packageContext, ShopsActivity.class);
            }
            break;
            case R.id.edit_product:{
                NavigateToActivity(packageContext, CreateProductActivity.class);
            }
            break;
            case R.id.index_products:{
                NavigateToActivity(packageContext, ProductIndexActivity.class);
            }
            break;
        }
    }

    public void NavigateToActivity(Context packageContext, Class activityClass){
        Intent intent = new Intent(packageContext, activityClass);
        packageContext.startActivity(intent);
    }

    public void DisableCurrentActivityMenuOption(AppCompatActivity packageContext, Menu menu){
      switch (packageContext.getTitle().toString()){
          case "MainActivity":{
              MenuItem item= menu.findItem(R.id.view_transactions);
              item.setEnabled(false);
          }
      }
    }

}
