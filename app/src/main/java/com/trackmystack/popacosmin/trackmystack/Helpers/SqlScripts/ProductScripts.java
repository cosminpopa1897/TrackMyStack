package com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts;

/**
 * Created by Cosmin on 01-May-18.
 */

public class ProductScripts {

      static class ProductColumns {
        public static final String idCol = "Id";
        public static final String nameCol = "Name";
        public static final String descriptionCol = "Description";
        public static final String priceCol = "Price";
        public static final String isDeletedColumn = "IsDeleted";

    }

    static class ProductColumnAttributes {
        public static final String idAttributes =" INTEGER NOT NULL PRIMARY KEY";
        public static final String nameAttributes = "TEXT NOT NULL";
        public static final String descriptionAttributes = "TEXT" ;
        public static final String priceAttribute = "REAL NOT NULL DEFAULT 0";
        public static final String isDeletedAttribUte = "NUMERIC NOT NULL DEFAULT 0";
    }

    public static String productTable = "Product";


    private static final String createTable = "CREATE TABLE " + productTable +"("
            + ProductColumns.idCol + " " +ProductColumnAttributes.idAttributes + ","
            + ProductColumns.nameCol + " " + ProductColumnAttributes.nameAttributes + ","
            + ProductColumns.descriptionCol + " " + ProductColumnAttributes.descriptionAttributes + ","
            + ProductColumns.priceCol + " " + ProductColumnAttributes.priceAttribute + ","
            + ProductColumns.isDeletedColumn + " " + ProductColumnAttributes.isDeletedAttribUte + ")";


    public static String getCreateScripts() {
        return createTable;
    }

    public static String getUpdateScripts() {
        return null;
    }
}
