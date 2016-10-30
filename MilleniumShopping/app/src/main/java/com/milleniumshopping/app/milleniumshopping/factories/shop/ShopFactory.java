package com.milleniumshopping.app.milleniumshopping.factories.shop;

import com.milleniumshopping.app.milleniumshopping.domain.shop.Shop;

import java.io.Serializable;

/**
 * Created by Admin on 2016/04/15.
 */
public class ShopFactory implements Serializable {
    
    public static Shop getShop(String shopNumber, String shopName, String shopOwner, String shopPhoneNumber)
    {
        Shop shop = new Shop.Builder()
                        .shopNumber(shopNumber)
                        .shopName(shopName)
                        .shopOwner(shopOwner)
                        .shopPhoneNumber(shopPhoneNumber)
                        .build();
    
        return shop;
    }
}
