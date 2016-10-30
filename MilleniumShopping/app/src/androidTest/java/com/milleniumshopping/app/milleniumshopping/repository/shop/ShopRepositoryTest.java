package com.milleniumshopping.app.milleniumshopping.repository.shop;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.shop.Shop;
import com.milleniumshopping.app.milleniumshopping.repository.shop.Impl.ShopRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;


/**
 * Write a description of class ShopRepositoryTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class ShopRepositoryTest extends AndroidTestCase
{
    public static final String TAG = "SHOP TEST";

    public void createReadUpdateDeleteTest() throws Exception
    {
        ShopRepository repository = new ShopRepositoryImpl(this.getContext());
        String shopNumber;

        //Create
        Shop createdShop = new Shop.Builder()
                .shopNumber("1000")
                .shopName("Name")
                .shopOwner("Owner")
                .shopPhoneNumber("Phone number")
                .build();

        Shop insertedShop = repository.save(createdShop);
        shopNumber = insertedShop.getShopNumber();
        Assert.assertNotNull(TAG + " CREATE", insertedShop);

        //Read all
        Set<Shop> shops = repository.findAll();
        Assert.assertNotNull(TAG + " READ ALL", shops.size() > 0);

        //Read entity
        Shop shop = repository.findById(createdShop.getShopNumber(), createdShop.getShopName());
        Assert.assertNotNull(TAG + " READ SHOP", shop);

        //Update shop
        Shop updateShop = new Shop.Builder()
                .copy(shop)
                .shopNumber("1000")
                .build();

        repository.update(updateShop);
        Shop newShop = repository.findById(updateShop.getShopNumber(), updateShop.getShopName());
        Assert.assertEquals(TAG + " UPDATE SHOP", "NewShopNumber", newShop.getShopNumber());

        //Delete shop
        repository.delete(updateShop);
        Shop deletedShop = repository.findById(updateShop.getShopNumber(), updateShop.getShopName());
        Assert.assertNull(TAG + " DELETE", deletedShop);
    }
}