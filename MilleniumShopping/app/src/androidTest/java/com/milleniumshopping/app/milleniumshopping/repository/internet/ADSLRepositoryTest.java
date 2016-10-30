package com.milleniumshopping.app.milleniumshopping.repository.internet;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.internet.ADSL;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.ADSLRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ADSLRepositoryTest extends AndroidTestCase{
    private static final String TAG = "ADSL TEST";

    public void createReadUpdateDeleteTest() throws Exception
    {
        ADSLRepository adslRepository = new ADSLRepositoryImpl(this.getContext());
        String ipAddress;

        //Create
        ADSL createdInternet = new ADSL.Builder()
                .ipAddress("10.1.1.1")
                .ISP("Telkom")
                .planName("Capped ADSL")
                .price("R399")
                .dataAllowance("100GB")
                .type("ADSL")
                .build();

        ADSL insertedInternet = adslRepository.save(createdInternet);
        ipAddress = insertedInternet.getIPAddress();
        Assert.assertNotNull(TAG + " CREATE", insertedInternet);

        //Read all
        Set<ADSL> internetServices = adslRepository.findAll();
        Assert.assertTrue(TAG + " READ ALL", internetServices.size() > 0);

        //Read entity
        ADSL internet = adslRepository.findById(createdInternet.getIPAddress(), createdInternet.getType());
        Assert.assertNotNull(TAG + " READ ADSL", internet);

        //Update internet
        ADSL updateInternet = new ADSL.Builder()
                .copy(internet)
                .ipAddress("NewIP")
                .build();
        adslRepository.update(updateInternet);
        ADSL newInternet = adslRepository.findById(updateInternet.getIPAddress(), updateInternet.getType());
        Assert.assertEquals(TAG + " UPDATE ADSL", "NewIP", newInternet.getIPAddress());

        //Delete employee
        adslRepository.delete(updateInternet);
        ADSL deletedInternet = adslRepository.findById(updateInternet.getIPAddress(), updateInternet.getType());
        Assert.assertNull(TAG + " DELETE", deletedInternet);
    }
}
