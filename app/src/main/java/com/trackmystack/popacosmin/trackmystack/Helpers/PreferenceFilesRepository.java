package com.trackmystack.popacosmin.trackmystack.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trackmystack.popacosmin.trackmystack.Models.Order;
import com.trackmystack.popacosmin.trackmystack.Models.Product;
import com.trackmystack.popacosmin.trackmystack.Models.Shop;
import com.trackmystack.popacosmin.trackmystack.Models.Transaction;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cosmin on 08-Dec-17.
 */

public class PreferenceFilesRepository<T> {

        public void saveObject(SharedPreferences sharedPreferences, Object object, Type objectType, String objectKey){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String jsonString = gson.toJson(object, objectType);
            editor.putString(objectKey, jsonString);
            editor.apply();
        }

        public T loadObject(SharedPreferences sharedPreferences, String objectKey, Type objectType){
            String jsonString = sharedPreferences.getString(objectKey, null);
            Gson gson = new Gson();
            T productList = gson.fromJson(jsonString, objectType);
            return productList;
        }

}
