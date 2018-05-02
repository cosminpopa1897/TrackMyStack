package com.trackmystack.popacosmin.trackmystack.Models;

import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.trackmystack.popacosmin.trackmystack.Helpers.BundleHelpers;

import java.lang.reflect.Type;

/**
 * Created by Cosmin on 08-Dec-17.
 */

public class Shop {
    public int Id;
    public String Name;
    public String City;
    public String Country;
    public boolean IsDeleted;


    public final static Bundle bundleShop(Shop shop){
        Type shopType = new TypeToken<Shop>(){}.getType();
        Bundle bundle = BundleHelpers.Pack(shop, shopType, "shop");
        return  bundle;
    }

    public final static Shop unBundleShop(Bundle bundle){
        Type shopType = new TypeToken<Shop>(){}.getType();
        Shop newShop = BundleHelpers.UnPack(bundle, "shop", shopType);
        return newShop;
    }
}
