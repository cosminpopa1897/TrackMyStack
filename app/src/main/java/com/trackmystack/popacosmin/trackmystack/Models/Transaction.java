package com.trackmystack.popacosmin.trackmystack.Models;

import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.trackmystack.popacosmin.trackmystack.Helpers.BundleHelpers;
import com.trackmystack.popacosmin.trackmystack.Helpers.DateFormatter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cosmin on 08-Dec-17.
 */

public class Transaction {
    public int Id;
    public String Name;
    public int ProductId;
    public Product Product;
    public int ReceiverShopId;
    public Shop ReceiverShop;
    public String Receiver;
    public int SenderShopId;
    public Shop SenderShop;
    public String Sender;
    public Date DateSent;
    public Date DateReceived;
    public boolean IsDeleted;
    public float Quantity;

    public static Bundle BundleTransaction(Transaction Transaction){
        Type transactionType = new TypeToken<Transaction>(){}.getType();
        Bundle newBundle = BundleHelpers.Pack(Transaction,transactionType, "transaction");
        return newBundle;
    }

    public static Transaction UnpackTransaction(Bundle bundle){
        Type transactionType = new TypeToken<Transaction>(){}.getType();
        Transaction newTransaction = BundleHelpers.UnPack(bundle,"transaction", transactionType);
        return newTransaction;
    }

}
