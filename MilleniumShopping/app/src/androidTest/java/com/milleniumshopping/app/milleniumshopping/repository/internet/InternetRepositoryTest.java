package com.milleniumshopping.app.milleniumshopping.repository.internet;
 /* Write a description of class TestInternet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.internet.Internet;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.InternetRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;


public class InternetRepositoryTest extends AndroidTestCase
{
    private static final String TAG = "INTERNET TEST";
    
    public void createReadUpdateDeleteTest() throws Exception
    {
        InternetRepository repository = new InternetRepositoryImpl(this.getContext());
        String ipAddress;
        
        //Create
        Internet createdInternet = new Internet.Builder()
                                            .ipAddress("10.1.1.1")
                                            .ISP("Telkom")
                                            .planName("Capped ADSL")
                                            .price("R399")
                                            .dataAllowance("100GB")
                                            .type("ADSL")
                                            .build();
                                            
        Internet insertedInternet = repository.save(createdInternet);
        ipAddress = insertedInternet.getIPAddress();
        Assert.assertNotNull(TAG + " CREATE", insertedInternet);
        
        //Read all
        Set<Internet> internetServices = repository.findAll();
        Assert.assertTrue(TAG + " READ ALL", internetServices.size() > 0);
        
        //Read entity
        Internet internet = repository.findById(createdInternet.getIPAddress(), createdInternet.getType());
        Assert.assertNotNull(TAG + " READ INTERNET", internet);
        
        //Update internet
        Internet updateInternet = new Internet.Builder()
                                            .copy(internet)
                                            .ipAddress("NewIP")
                                            .build();
        repository.update(updateInternet);
        Internet newInternet = repository.findById(updateInternet.getIPAddress(), updateInternet.getType());
        Assert.assertEquals(TAG + " UPDATE INTERNET", "NewIP", newInternet.getIPAddress());
        
        //Delete employee
        repository.delete(updateInternet);
        Internet deletedInternet = repository.findById(updateInternet.getIPAddress(), updateInternet.getType());
        Assert.assertNull(TAG + " DELETE", deletedInternet);
    }
}
