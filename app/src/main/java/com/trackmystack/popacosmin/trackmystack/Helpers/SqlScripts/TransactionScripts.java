package com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.trackmystack.popacosmin.trackmystack.Helpers.SqLiteHelper;
import com.trackmystack.popacosmin.trackmystack.Models.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cosmin on 01-May-18.
 */

public class TransactionScripts {

    public static class TransactionColumns{
        public static final String idCol = "id";
        public static final String nameCol = "name";
        public static final String productIdCol = "ProductId";
        public static final String senderShopIdCol = "SenderShopId";
        public static final String receiverShopIdCol = "ReceiverShopId";
        public static final String dateSentCol = "DateSent";
        public static final String dateReceivedCol = "DateReceived";
        public static final String quantityCol = "Quantity";
        public static final String isDeletedCol = "IsDeleted";
    }

    public static class TransactionAttributes{
        public static final String idAttributes = "INTEGER NOT NULL PRIMARY KEY ";
        public static final String nameAttributes = "TEXT NOT NULL ";
        public static final String productIdAttributes ="INTEGER";
        public static final String senderShopIdAttributes = "INTEGER";
        public static final String receiverShopIdAttributes  = "INTEGER";
        public static final String dateSentAttributes = "INTEGER NOT NULL ";
        public static final String dateReceivedAttributes = "INTEGER";
        public static final String quantityAttributes = " REAL NOT NULL DEFAULT 0";
        public static final String isDeletedAttributes = " NUMERIC NOT NULL DEFAULT 0";
    }

    public static final String tableName = "Transactions";

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

    public static boolean insertTransaction(SQLiteDatabase db, Transaction transaction){
        ContentValues contentValues = getTransactionContentValues(transaction);
        long result = db.insert(tableName, null, contentValues);
        if (result == -1)
            return false;
        return true;
    }

    public static boolean updateTranasaction(SQLiteDatabase db, Transaction transaction){
        ContentValues contentValues = getTransactionContentValues(transaction);
        long result = db.update(tableName, contentValues, "_id="+transaction.Id, null);
        if (result == -1)
            return false;
        return true;
    }

    private static ContentValues getTransactionContentValues( Transaction transaction){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransactionColumns.nameCol, transaction.Name);
        contentValues.put(TransactionColumns.productIdCol, transaction.ProductId);
        contentValues.put(TransactionColumns.senderShopIdCol, transaction.SenderShopId);
        contentValues.put(TransactionColumns.receiverShopIdCol, transaction.ReceiverShopId);
        contentValues.put(TransactionColumns.dateSentCol, transaction.DateSent.getTime());
        contentValues.put(TransactionColumns.dateReceivedCol, transaction.DateReceived.getTime());
        contentValues.put(TransactionColumns.receiverShopIdCol, transaction.ReceiverShopId);
        contentValues.put(TransactionColumns.receiverShopIdCol, transaction.ReceiverShopId);
        contentValues.put(TransactionColumns.quantityCol, transaction.Quantity);
        contentValues.put(TransactionColumns.isDeletedCol, transaction.IsDeleted);
        return contentValues;
    }
    public static List<Transaction> getAllTransactions(SQLiteDatabase db){
        String selectQuery = "SELECT * FROM " + tableName;
        List<Transaction> transactionList =new ArrayList<Transaction>();


        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Transaction transaction =new Transaction();
                transaction.Id = Integer.parseInt(cursor.getString(0));
                transaction.Name =cursor.getString(1);
                transaction.ProductId = Integer.parseInt(cursor.getString(2));
                transaction.Product = ProductScripts.getProduct(db, transaction.ProductId);
                transaction.SenderShopId = Integer.parseInt(cursor.getString(3));
                transaction.SenderShop = ShopScripts.getShop(db, transaction.SenderShopId);
                transaction.ReceiverShopId = Integer.parseInt(cursor.getString(4));
                transaction.ReceiverShop = ShopScripts.getShop(db, transaction.ReceiverShopId);
                transaction.DateSent = new Date(Long.parseLong(cursor.getString(5)));
                transaction.DateReceived = new Date(Long.parseLong(cursor.getString(6)));
                transaction.Quantity = Float.parseFloat(cursor.getString(7));
                transaction.IsDeleted = Boolean.parseBoolean(cursor.getString(8));
                transactionList.add(transaction);
            }
            while(cursor.moveToNext());
        }

        return transactionList;
    }

    public static  Transaction getTransaction(SQLiteDatabase db, int id){
        String query = "SELECT * FROM " + tableName
                +" WHERE " + TransactionColumns.idCol + "=" + Integer.toString(id);
        Transaction transaction = new Transaction();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                transaction.Id = Integer.parseInt(cursor.getString(0));
                transaction.Name =cursor.getString(1);
                transaction.ProductId = Integer.parseInt(cursor.getString(2));
                transaction.Product = ProductScripts.getProduct(db, transaction.ProductId);
                transaction.SenderShopId = Integer.parseInt(cursor.getString(3));
                transaction.SenderShop = ShopScripts.getShop(db, transaction.SenderShopId);
                transaction.ReceiverShopId = Integer.parseInt(cursor.getString(4));
                transaction.ReceiverShop = ShopScripts.getShop(db, transaction.ReceiverShopId);
                transaction.DateSent = new Date(Long.parseLong(cursor.getString(5)));
                transaction.DateReceived = new Date(Long.parseLong(cursor.getString(6)));
                transaction.Quantity = Float.parseFloat(cursor.getString(7));
                transaction.IsDeleted = Boolean.parseBoolean(cursor.getString(8));
            }
            while(cursor.moveToNext());
        }

        return transaction;

    }
}
