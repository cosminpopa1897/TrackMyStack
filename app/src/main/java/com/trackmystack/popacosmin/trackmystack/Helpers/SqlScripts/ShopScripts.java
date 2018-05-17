package com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.trackmystack.popacosmin.trackmystack.Models.Shop;
import com.trackmystack.popacosmin.trackmystack.Models.StockItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cosmin on 01-May-18.
 */

public class ShopScripts  {

    public class ShopColumns{
        public final static String idCol = "id";
        public final static String nameCol = "name";
        public final static String cityCol = "City";
        public final static String countryCol = "Country";
        public final static String isDeletedCol = "IsDeleted";

    }

    public class ShopAttributes{
        public final static String idAttributes = " INTEGER NOT NULL PRIMARY KEY";
        public final static String nameAttributes = "TEXT NOT NULL ";
        public final static String cityAttributes = " TEXT NOT NULL";
        public final static String countyAttributes = "TEXT NOT NULL ";
        public final static String isDeletedAttributes = "NUMERIC NOT NULL DEFAULT 0";
    }

    public final static String tableName = "Shop";


    private final static String createTable = "CREATE TABLE " + ShopScripts.tableName + "("
            + ShopColumns.idCol + " " + ShopAttributes.idAttributes + ", "
            + ShopColumns.nameCol + " " + ShopAttributes.nameAttributes + ", "
            + ShopColumns.cityCol + " " + ShopAttributes.cityAttributes + ", "
            + ShopColumns.countryCol + " " + ShopAttributes.countyAttributes + ", "
            + ShopColumns.isDeletedCol + " " + ShopAttributes.isDeletedAttributes + ") ";

    public static String getCreateScripts() {
        return createTable;
    }


    public static String getUpdateScripts() {
        return null;
    }

    public static boolean insertShop(SQLiteDatabase db, Shop shop){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ShopColumns.nameCol, shop.Name);
        contentValues.put(ShopColumns.cityCol, shop.City);
        contentValues.put(ShopColumns.countryCol, shop.Country);
        contentValues.put(ShopColumns.isDeletedCol, shop.IsDeleted);
        long result = db.insert(tableName, null, contentValues);
        if (result == -1)
            return false;
        return true;
    }

    public static List<Shop> getAllShops(SQLiteDatabase db){
        String selectQuery = "SELECT * FROM " + tableName;
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Shop> shopList = new ArrayList<Shop>();

        if(cursor.moveToFirst()){
            do{
                Shop shop =new Shop();
                shop.Id = Integer.parseInt(cursor.getString(0));
                shop.Name =cursor.getString(1);
                shop.City =cursor.getString(2);
                shop.Country = cursor.getString(3);
                shop.IsDeleted = Boolean.parseBoolean(cursor.getString(4));
                shop.StockItems = (ArrayList<StockItem>) StockItemScripts.getStockItemsByShop(db, shop.Id);
                shopList.add(shop);
            }
            while(cursor.moveToNext());
        }

        return shopList;
    }

    public static Shop getShop(SQLiteDatabase db, int id){
        String query = "SELECT * FROM " + tableName
                +" WHERE " + ShopColumns.idCol + "=" + Integer.toString(id);

        Cursor cursor = db.rawQuery(query, null);
        Shop shop = new Shop();

        if(cursor.moveToFirst()){
            do{
                shop.Id = Integer.parseInt(cursor.getString(0));
                shop.Name =cursor.getString(1);
                shop.City =cursor.getString(2);
                shop.Country = cursor.getString(3);
                shop.IsDeleted = Boolean.parseBoolean(cursor.getString(4));
                shop.StockItems = (ArrayList<StockItem>) StockItemScripts.getStockItemsByShop(db, shop.Id);
            }
            while(cursor.moveToNext());
        }

        return shop;
    }


}
