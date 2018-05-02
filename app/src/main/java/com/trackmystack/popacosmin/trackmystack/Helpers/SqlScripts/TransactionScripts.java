package com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts;

/**
 * Created by Cosmin on 01-May-18.
 */

public class TransactionScripts {

    public static class TransactionColumns{
        public static final String idCol = "Id";
        public static final String nameCol = "Name";
        public static final String productIdCol = "ProductId";
        public static final String senderShopIdCol = "SenderShopId";
        public static final String receiverShopIdCol = "ReceiverShopId";
        public static final String dateSentCol = "DateSent";
        public static final String dateReceivedCol = "DateReceived";
        public static final String quantityCol = "Quantity";
        public static final String isDeletedCol = "isDeleted";
    }

    public static class TransactionAttributes{
        public static final String idAttributes = "INTEGER NOT NULL PRIMARY KEY ";
        public static final String nameAttributes = "TEXT NOT NULL ";
        public static final String productIdAttributes ="INTEGER";
        public static final String senderShopIdAttributes = "INTEGER";
        public static final String receiverShopIdAttributes  = "INTEGER";
        public static final String dateSentAttributes = "NUMERIC NOT NULL ";
        public static final String dateReceivedAttributes = "NUMERIC NOT NULL ";
        public static final String quantityAttributes = " REAL NOT NULL DEFAULT 0";
        public static final String isDeletedAttributes = " NUMERIC NOT NULL DEFAULT 0";
    }

    public static final String tableName = "Transaction";

    private static final String createTable = "CREATE TABLE " + tableName + "("
            + TransactionColumns.idCol + " " + TransactionAttributes.idAttributes + ", "
            + TransactionColumns.nameCol + " " + TransactionAttributes.nameAttributes + ", "
            + TransactionColumns.productIdCol + " " + TransactionAttributes.productIdAttributes + ", "
            + TransactionColumns.senderShopIdCol + " " + TransactionAttributes.senderShopIdAttributes + ", "
            + TransactionColumns.receiverShopIdCol + " " + TransactionAttributes.receiverShopIdAttributes + ", "
            + TransactionColumns.dateSentCol + " " + TransactionAttributes.dateSentAttributes + ", "
            + TransactionColumns.dateReceivedCol + " " + TransactionAttributes.dateReceivedAttributes + ", "
            + TransactionColumns.quantityCol + " " + TransactionAttributes.quantityAttributes + ", "
            + TransactionColumns.isDeletedCol + " " + TransactionAttributes.isDeletedAttributes + ","
            + "FOREIGN KEY(" + TransactionColumns.senderShopIdCol + ")" + " REFERENCES " + ShopScripts.tableName + "(" + ShopScripts.ShopColumns.idCol + "),"
            + "FOREIGN KEY(" + TransactionColumns.receiverShopIdCol + ")" + " REFERENCES " + ShopScripts.tableName + "(" + ShopScripts.ShopColumns.idCol + "))";





    public static String getCreateScripts() {
        return createTable;
    }

    public static String getUpdateScripts() {
        return null;
    }
}
