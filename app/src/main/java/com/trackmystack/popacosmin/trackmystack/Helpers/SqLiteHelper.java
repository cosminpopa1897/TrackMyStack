package com.trackmystack.popacosmin.trackmystack.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts.ProductScripts;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts.StockItemScripts;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts.ShopScripts;
import com.trackmystack.popacosmin.trackmystack.Helpers.SqlScripts.TransactionScripts;
import com.trackmystack.popacosmin.trackmystack.Models.Product;
import com.trackmystack.popacosmin.trackmystack.Models.Shop;
import com.trackmystack.popacosmin.trackmystack.Models.StockItem;
import com.trackmystack.popacosmin.trackmystack.Models.Transaction;

import java.util.ArrayList;

/**
 * Created by Cosmin on 10-Dec-17.
 */

public class SqLiteHelper extends SQLiteOpenHelper {

    private final static String databaseName = Constants.DatabaseKeys.sqlLiteDatabaseName;
    private final static int version = 1;
    private static  SqLiteHelper instance = null;

    private  SqLiteHelper(Context context ) {
        super(context,  databaseName, null, version);
    }

    public static synchronized SqLiteHelper getSqLiteHelperInstance(Context context){
        if (instance == null)
            instance = new SqLiteHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(ProductScripts.getCreateScripts());
        sqLiteDatabase.execSQL(ShopScripts.getCreateScripts());
        sqLiteDatabase.execSQL(TransactionScripts.getCreateScripts());
        sqLiteDatabase.execSQL(StockItemScripts.getCreateScripts());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int v){
        sqLiteDatabase.execSQL(ProductScripts.getUpdateScripts());
        sqLiteDatabase.execSQL(ShopScripts.getUpdateScripts());
        sqLiteDatabase.execSQL(TransactionScripts.getUpdateScripts());
        sqLiteDatabase.execSQL(StockItemScripts.getUpdateScripts());
    }




    //region ProductRepo
    public boolean insertProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result =ProductScripts.insertProduct(db, product);
        db.close();
        return result;
    }

    public boolean updateProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = ProductScripts.updateProduct(db, product);
        db.close();
        return result;
    }

    public ArrayList<Product> getAllProducts(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Product> productList = (ArrayList<Product>) ProductScripts.getAllProducts(db);
        db.close();
        return productList;
    }

    public Product getProduct(int id){
        SQLiteDatabase db = getReadableDatabase();
        Product product = ProductScripts.getProduct(db, id);
        db.close();
        return product;
    }

    public boolean updateProduct(int id, Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.Name);
        contentValues.put("Description", product.Description);
        contentValues.put("Price", product.Price);
        contentValues.put("IsDeleted", product.IsDeleted);
        long result = db.update("Product", contentValues, "id = ?", new String[] {Integer.toString(id)});
        if (result == -1)
            return false;
        return true;
    }

    public Integer deleteProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Product", "id = ?", new String[] {Integer.toString((id))});

    }


    //endregion

    //region ShopRepo
    public boolean insertShop(Shop shop){
        SQLiteDatabase db = getWritableDatabase();
        boolean result = ShopScripts.insertShop(db, shop);
        db.close();
        return result;
    }

    public boolean updateShop(Shop shop){
        SQLiteDatabase db = getWritableDatabase();
        boolean result = ShopScripts.updateShop(db, shop);
        db.close();
        return result;
    }

    public ArrayList<Shop> getAllShops(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Shop> shopList =(ArrayList<Shop>) ShopScripts.getAllShops(db);
        db.close();
        return shopList;
    }

    public Shop getShop(int shopId){
        SQLiteDatabase db = getReadableDatabase();
        Shop shop = ShopScripts.getShop(db, shopId);
        db.close();
        return shop;
    }
    //endregion

    //region TransactionRepo
    public boolean insertTransaction(Transaction transaction){
        SQLiteDatabase db = getWritableDatabase();
        boolean result = TransactionScripts.insertTransaction(db, transaction);
        db.close();
        return result;
    }

    public boolean updateTransaction(Transaction transaction){
        SQLiteDatabase db = getWritableDatabase();
        boolean result = TransactionScripts.updateTranasaction(db, transaction);
        db.close();
        return result;
    }

    public ArrayList<Transaction> getAllTranasactions(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Transaction> transactionList = (ArrayList<Transaction>) TransactionScripts.getAllTransactions(db);
        db.close();
        return transactionList;
    }

    public Transaction getTransaction(int transactionId){
        SQLiteDatabase db = getReadableDatabase();
        Transaction transaction = TransactionScripts.getTransaction(db, transactionId);
        db.close();
        return transaction;
    }
    //endregion

    //region StockItemRepo
    public boolean insertStockItem(StockItem stockItem){
        SQLiteDatabase db = getWritableDatabase();
        boolean result = StockItemScripts.insertProduct(db, stockItem);
        db.close();
        return result;
    }

    public boolean updateStockItem(StockItem stockItem){
        SQLiteDatabase db = getWritableDatabase();
        boolean result = StockItemScripts.updateProduct(db, stockItem);
        db.close();
        return result;
    }

    public ArrayList<StockItem> getAllStockItems(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<StockItem> stockItemList = (ArrayList<StockItem>) StockItemScripts.getAllStockItems(db);
        db.close();
        return stockItemList;
    }

    public ArrayList<StockItem> getStockItemsByShop(int shopId){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<StockItem> stockItemList = (ArrayList<StockItem>) StockItemScripts.getStockItemsByShop(db, shopId);
        db.close();
        return stockItemList;
    }
    public StockItem getStockItem(int stockItemId){
        SQLiteDatabase db = getReadableDatabase();
        StockItem stockItem = StockItemScripts.getStockItem(db, stockItemId);
        db.close();
        return stockItem;
    }
    //endregion
}