package com.milleniumshopping.app.milleniumshopping.conf.security;
/**
 * Created by Chad on 4/21/2016.
 */

import junit.framework.Assert;

public class LoginTest {

    public void checkLoginTest()
    {
        Login object = new Login();
        
        Assert.assertEquals(object.checkLogin(), true);
    }
}
