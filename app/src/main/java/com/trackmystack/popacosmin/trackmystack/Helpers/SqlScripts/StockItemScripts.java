package com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.trackmystack.popacosmin.trackmystack.Models.StockItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cosmin on 01-May-18.
 */

public class StockItemScripts {

    public final static String tableName = "StockItem";

    public static class StockItemColumns {
        public static  final String idCol = "id";
        public static final String productIdCol = "ProductId";
        public static final String shopIdCol = "ShopId";
        public static final String quantityCol = "Quantity";
        public static final String isDeletedCol = "IsDeleted";
    }

    public static class StockItemAttributes {
        public static final String idAttributes = " INTEGER NOT NULL PRIMARY KEY";
        public static final String productIdAttributes = " INTEGER";
        public static final String shopIdAttributs = " INTEGER ";
        public static final String quantityAttributes = " REAL NOT NULL DEFAULT 0";
        public static final String isDeletedAttributes = "NUMERIC NOT NULL DEFAULT 0";

    }

    private static final String createTable = "CREATE TABLE " + tableName + "("
            + StockItemColumns.idCol + " " + StockItemAttributes.idAttributes + ","
            + StockItemColumns.productIdCol + " " + StockItemAttributes.productIdAttributes + ","
            + StockItemColumns.shopIdCol + " " + StockItemAttributes.shopIdAttributs + ","
            + StockItemColumns.quantityCol + " " + StockItemAttributes.quantityAttributes + ","
            + StockItemColumns.isDeletedCol + " " + StockItemAttributes.isDeletedAttributes + ","
            +  "FOREIGN KEY(" + StockItemColumns.productIdCol + ")" + " REFERENCES " + ProductScripts.productTable + "(" + ProductScripts.ProductColumns.idCol + "),"
            +  "FOREIGN KEY(" + StockItemColumns.shopIdCol+ ")" + " REFERENCES " + ShopScripts.tableName + "(" + ShopScripts.ShopColumns.idCol + "))";


    public static String getCreateScripts(){
        return createTable;
    }

    public static String getUpdateScripts(){
        return null;
    }

    public static boolean insertProduct(SQLiteDatabase db, StockItem stockItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(StockItemColumns.productIdCol, stockItem.ProductId);
        contentValues.put(StockItemColumns.shopIdCol, stockItem.ShopId);
        contentValues.put(StockItemColumns.quantityCol, stockItem.Quantity);
        contentValues.put(StockItemColumns.isDeletedCol, stockItem.IsDeleted);
        long result = db.insert(tableName, null, contentValues);
        if (result == -1)
            return false;
        return true;
    }

    public static List<StockItem> getAllStockItems(SQLiteDatabase db){
        String selectQuery = "SELECT * FROM " + tableName;
        List<StockItem> stockItemList =new ArrayList<StockItem>();


        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                StockItem stockItem =new StockItem();
                stockItem.Id = Integer.parseInt(cursor.getString(0));
                stockItem.ProductId = Integer.parseInt(cursor.getString(1));
                stockItem.ShopId = Integer.parseInt(cursor.getString(2));
                stockItem.Quantity = Float.parseFloat(cursor.getString(3));
                stockItem.IsDeleted = Boolean.parseBoolean(cursor.getString(4));
                stockItemList.add(stockItem);
            }
            while(cursor.moveToNext());
        }

        return stockItemList;
    }

    public static List<StockItem> getStockItemsByShop(SQLiteDatabase db, int shopId){
        String selectQuery = "SELECT * FROM " + tableName
                +" WHERE " + StockItemColumns.shopIdCol + "=" + Integer.toString(shopId);
        List<StockItem> stockItemList =new ArrayList<StockItem>();


        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                StockItem stockItem =new StockItem();
                stockItem.Id = Integer.parseInt(cursor.getString(0));
                stockItem.ProductId = Integer.parseInt(cursor.getString(1));
                stockItem.ShopId = Integer.parseInt(cursor.getString(2));
                stockItem.Quantity = Float.parseFloat(cursor.getString(3));
                stockItem.IsDeleted = Boolean.parseBoolean(cursor.getString(4));
                stockItemList.add(stockItem);
            }
            while(cursor.moveToNext());
        }

        return stockItemList;
    }

    public static StockItem getStockItem(SQLiteDatabase db, int id){
        String query = "SELECT * FROM " + tableName
                +" WHERE " + StockItemColumns.idCol + "=" + Integer.toString(id);
        Cursor cursor = db.rawQuery(query, null);
        StockItem stockItem =  new StockItem();

        if(cursor.moveToFirst()){
            do{
                stockItem.Id = Integer.parseInt(cursor.getString(0));
                stockItem.ProductId = Integer.parseInt(cursor.getString(1));
                stockItem.ShopId = Integer.parseInt(cursor.getString(2));
                stockItem.Quantity = Float.parseFloat(cursor.getString(3));
                stockItem.IsDeleted = Boolean.parseBoolean(cursor.getString(4));
            }
            while(cursor.moveToNext());
        }
        return stockItem;
    }

}
