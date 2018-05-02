package com.trackmystack.popacosmin.trackmystack.Helpers;

/**
 * Created by Cosmin on 08-Dec-17.
 */

public final class IdentityGenerator {
    private static int ProductId = 0;
    private static int OrderId = 0;
    private static int TransactionId = 0;
    private static int ShopId = 0;

    private IdentityGenerator(){}

    public static int getProductId(){
        return ++ProductId;
    }

    public static int getOrderId(){
        return ++OrderId;
    }

    public static int getTransactionId(){
        return ++TransactionId;
    }

    public static int getShopId(){
        return ++ShopId;
    }
}
