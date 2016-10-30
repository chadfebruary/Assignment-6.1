package com.milleniumshopping.app.milleniumshopping.repository.shop;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.shop.ShoppingCentre;
import com.milleniumshopping.app.milleniumshopping.repository.shop.Impl.ShoppingCentreRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ShoppingCentreRepositoryTest extends AndroidTestCase{
    public static final String TAG = "SHOPPINGCENTRE TEST";

    public void createReadUpdateDeleteTest() throws Exception
    {
        ShoppingCentreRepository shoppingCentreRepository = new ShoppingCentreRepositoryImpl(this.getContext());
        String shopNumber;

        //Create
        ShoppingCentre createdShop = new ShoppingCentre.Builder()
                .centreName("1000")
                .centreCode("Name")
                .portfolio("Owner")
                .address("Phone number")
                .build();

        ShoppingCentre insertedShop = shoppingCentreRepository.save(createdShop);
        shopNumber = insertedShop.getCentreName();
        Assert.assertNotNull(TAG + " CREATE", insertedShop);

        //Read all
        Set<ShoppingCentre> shops = shoppingCentreRepository.findAll();
        Assert.assertNotNull(TAG + " READ ALL", shops.size() > 0);

        //Read entity
        ShoppingCentre shop = shoppingCentreRepository.findById(createdShop.getCentreName(), createdShop.getCentreName());
        Assert.assertNotNull(TAG + " READ SHOPPINGCENTRE", shop);

        //Update shop
        ShoppingCentre updateShop = new ShoppingCentre.Builder()
                .copy(shop)
                .centreName("1000")
                .build();

        shoppingCentreRepository.update(updateShop);
        ShoppingCentre newShop = shoppingCentreRepository.findById(updateShop.getCentreName(), updateShop.getCentreName());
        Assert.assertEquals(TAG + " UPDATE SHOPPINGCENTRE", "NewShopNumber", newShop.getCentreName());

        //Delete shop
        shoppingCentreRepository.delete(updateShop);
        ShoppingCentre deletedShop = shoppingCentreRepository.findById(updateShop.getCentreName(), updateShop.getCentreCode());
        Assert.assertNull(TAG + " DELETE", deletedShop);
    }
}
