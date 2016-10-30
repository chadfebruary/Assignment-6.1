package com.milleniumshopping.app.milleniumshopping.conf.databases; /**
 * Write a description of class DBConstantsTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import android.test.AndroidTestCase;

import junit.framework.Assert;

public class DBConstantsTest extends AndroidTestCase
{
    public void databaseNameTest()
    {
        Assert.assertEquals(DBConstants.DATABASE_NAME, "MilleniumInfinity");
    }

    public void databaseVersionTest()
    {
        Assert.assertEquals(DBConstants.DATABASE_VERSION, 1);
    }
}
