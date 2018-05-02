package com.trackmystack.popacosmin.trackmystack.Helpers;

import android.os.Bundle;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Cosmin on 30-Apr-18.
 */

public class BundleHelpers {

    public final static Bundle Pack(Object object, Type objectType, String keyValue){
        Gson gson = new Gson();
        String newJsonString  = gson.toJson(object, objectType);
        Bundle newBundle = new Bundle();
        newBundle.putString(keyValue, newJsonString);
        return newBundle;
    }

    public final static <T> T UnPack(Bundle bundle, String keyValue, Type objectType){
        Gson gson = new Gson();
        String json = bundle.getString(keyValue);
        T unpackedObject = gson.fromJson(json, objectType);
        return unpackedObject;
    }
}
