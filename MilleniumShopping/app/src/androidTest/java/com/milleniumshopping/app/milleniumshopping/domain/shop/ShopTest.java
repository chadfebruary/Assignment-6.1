package com.milleniumshopping.app.milleniumshopping.domain.shop;

import junit.framework.Assert;




/**
 * Created by Chad on 4/24/2016.
 */
public class ShopTest{

    Shop shop = new Shop.Builder()
            .shopNumber("ShopNumber")
            .shopName("ShopName")
            .shopOwner("ShopOwner")
            .shopPhoneNumber("ShopPhoneNumber")
            .build();


        public void testBuildShop() throws Exception
        {
            Assert.assertEquals(shop.getShopNumber(), "ShopNumber");
            Assert.assertEquals(shop.getShopName(), "ShopName");
            Assert.assertEquals(shop.getShopOwner(), "ShopOwner");
            Assert.assertEquals(shop.getShopPhoneNumber(), "ShopPhoneNumber");
        }
}
