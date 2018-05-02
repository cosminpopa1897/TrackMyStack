package com.trackmystack.popacosmin.trackmystack.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.trackmystack.popacosmin.trackmystack.Models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cosmin on 10-Dec-17.
 */

public class SqLiteHelper extends SQLiteOpenHelper {

    public String createProductTable =
            "CREATE TABLE Product(" +
            "Id TEXT primary key, " +
            "Name TEXT not null," +
            "Description TEXT," +
            "Price REAL not null); ";

    public String createOrderTable =
            "CREATE TABLE Order(" +
            "Id TEXT primary key," +
            "Quantity REAL not null," +
            "TransactionId int not null," +
                    "FOREIGN KEY (TransactionId) REFERENCES Transaction(Id)); ";

    public String createProductToOrderTable =
            "CREATE TABLE ProductToOrder(" +
            "Id INTEGER primary key," +
            "OrderId INTEGER not null," +
            "ProductId INTEGER not null," +
            "FOREIGN KEY (OrderId) REFERENCES Order(Id)," +
            "FOREIGN KEY (ProductId) REFERENCES Product(Id)); ";
    public SqLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public String createShopTable =
            "CREATE TABLE Shop(" +
                    "Id INTEGER primary key," +
                    "Address TEXT not null," +
                    "Note TEXT," +
                    "IsDeleted INTEGER not null)";
    public String createTransactionTable =
            "CREATE TABLE Transaction(" +
                    "Id INTEGER primary key," +
                    "ReceiverId INTEGER," +
                    "ReceiverName TEXT not null," +
                    "SenderId INTEGER," +
                    "SenderName TEXT not null," +
                    "DateSent TEXT not null," +
                    "DateReceived TEXT," +
                    "IsDeleted INTEGER not null," +
                    "FOREIGN KEY (ReceiverId) REFERENCES Shop(Id)," +
                    "FOREIGN KEY (SenderId) REFERENCES Shop(Id))" ;
    public String createStockTable =
            "CREATE TABLE Stock(" +
                    "Id INTEGER primary key," +
                    "ProductId INTEGER not null," +
                    "Quantity REAL not null," +
                    "ShopId INTEGER not null," +
                    "FOREIGN KEY (ProductId) REFERENCES Product(Id)," +
                    "FOREIGN KEY (ShopId) REFERENCES Shop(Id))";

    public String alterOrderTableAddColumn =
            "ALTER TABLE Order ADD COLUMN TransactionId INTEGER";

    public String alterOrderTableAddKeyToTransaction =
            "ALTER TABLE Order ADD CONSTRAINT fk_Order_Transaction" +
                    "FOREIGN KEY (TransactionId)" +
                    "REFERENCES Transaction(Id)";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(createProductTable + createOrderTable + createProductToOrderTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int v){
        sqLiteDatabase.execSQL(createShopTable + createTransactionTable + createStockTable + alterOrderTableAddColumn + alterOrderTableAddKeyToTransaction);
    }

    public boolean insertProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", product.Name);
        contentValues.put("Description", product.Description);
        contentValues.put("Price", product.Price);
        contentValues.put("IsDeleted", product.IsDeleted);
        long result = db.insert("Product", null, contentValues);
        if (result == -1)
            return false;
        return true;
    }

    public boolean updateProduct(int id, Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", product.Id);
        contentValues.put("Name", product.Name);
        contentValues.put("Description", product.Description);
        contentValues.put("Price", product.Price);
        contentValues.put("IsDeleted", product.IsDeleted);
        long result = db.update("Product", contentValues, "Id = ?", new String[] {Integer.toString(id)});
        if (result == -1)
            return false;
        return true;
    }

    public Integer deleteProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Product", "Id = ?", new String[] {Integer.toString((id))});

    }

    public List<Product> getAllProducts(){
        String selectQuery = "SELECT * FROM Product";
        List<Product> productList =new ArrayList<Product>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Product product =new Product();
                product.Id = Integer.parseInt(cursor.getString(0));
                product.Name =cursor.getString(1);
                product.Description =cursor.getString(2);
                product.Price = Float.parseFloat(cursor.getString(3));
                productList.add(product);
            }
            while(cursor.moveToNext());
        }

        return productList;
    }

}