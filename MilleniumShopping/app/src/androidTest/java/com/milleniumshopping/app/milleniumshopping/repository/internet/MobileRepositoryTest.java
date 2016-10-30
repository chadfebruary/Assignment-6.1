package com.milleniumshopping.app.milleniumshopping.repository.internet;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.internet.Mobile;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.MobileRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class MobileRepositoryTest extends AndroidTestCase {
    private static final String TAG = "MOBILE TEST";

    public void createReadUpdateDeleteTest() throws Exception
    {
        MobileRepository repository = new MobileRepositoryImpl(this.getContext());
        String ipAddress;

        //Create
        Mobile createdInternet = new Mobile.Builder()
                .ipAddress("10.1.1.1")
                .ISP("Telkom")
                .planName("Capped 4G")
                .price("R99")
                .dataAllowance("10GB")
                .type("4G")
                .build();

        Mobile insertedInternet = repository.save(createdInternet);
        ipAddress = insertedInternet.getIPAddress();
        Assert.assertNotNull(TAG + " CREATE", insertedInternet);

        //Read all
        Set<Mobile> internetServices = repository.findAll();
        Assert.assertTrue(TAG + " READ ALL", internetServices.size() > 0);

        //Read entity
        Mobile internet = repository.findById(createdInternet.getIPAddress(), createdInternet.getType());
        Assert.assertNotNull(TAG + " READ MOBILE", internet);

        //Update internet
        Mobile updateInternet = new Mobile.Builder()
                .copy(internet)
                .ipAddress("NewIP")
                .build();
        repository.update(updateInternet);
        Mobile newInternet = repository.findById(updateInternet.getIPAddress(), updateInternet.getType());
        Assert.assertEquals(TAG + " UPDATE MOBILE", "NewIP", newInternet.getIPAddress());

        //Delete employee
        repository.delete(updateInternet);
        Mobile deletedInternet = repository.findById(updateInternet.getIPAddress(), updateInternet.getType());
        Assert.assertNull(TAG + " DELETE", deletedInternet);
    }
}
