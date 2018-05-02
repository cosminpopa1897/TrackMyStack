package com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts;

/**
 * Created by Cosmin on 01-May-18.
 */

public class ProductToShopScripts {

    public final static String tableName = "ProductToShop";

    public static class ProductToShopsColumns{
        public static  final String idCol = "Id";
        public static final String productIdCol = "ProductId";
        public static final String shopIdCol = "ShopId";
    }

    public static class ProductToShopsAttributes{
        public static final String idAttributes = " INTEGER NOT NULL PRIMARY KEY";
        public static final String productIdAttributes = " INTEGER";
        public static final String shopIdAttributs = " INTEGER ";

    }

    private static final String createTable = "CREATE TABLE " + tableName + "("
            + ProductToShopsColumns.idCol + " " + ProductToShopsAttributes.idAttributes + ","
            + ProductToShopsColumns.productIdCol + " " + ProductToShopsAttributes.productIdAttributes + ","
            + ProductToShopsColumns.shopIdCol + " " + ProductToShopsAttributes.shopIdAttributs + ","
            +  "FOREIGN KEY(" + TransactionScripts.TransactionColumns.senderShopIdCol + ")" + " REFERENCES " + ShopScripts.tableName + "(" + ShopScripts.ShopColumns.idCol + "),"
            +  "FOREIGN KEY(" + TransactionScripts.TransactionColumns.receiverShopIdCol + ")" + " REFERENCES " + ShopScripts.tableName + "(" + ShopScripts.ShopColumns.idCol + "))";


    public static String getCreateScripts(){
        return createTable;
    }

    public static String getUpdateScripts(){
        return null;
    }

}
