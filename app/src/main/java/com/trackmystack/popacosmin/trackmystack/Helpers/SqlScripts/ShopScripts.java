package com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts;

/**
 * Created by Cosmin on 01-May-18.
 */

public class ShopScripts  {

    public class ShopColumns{
        public final static String idCol = "Id";
        public final static String nameCol = "Name";
        public final static String cityCol = "City";
        public final static String countyCol = "Country";
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
            + ShopColumns.countyCol + " " + ShopAttributes.countyAttributes + ", "
            + ShopColumns.isDeletedCol + " " + ShopAttributes.isDeletedAttributes + ") ";

    public static String getCreateScripts() {
        return createTable;
    }


    public static String getUpdateScripts() {
        return null;
    }
}
