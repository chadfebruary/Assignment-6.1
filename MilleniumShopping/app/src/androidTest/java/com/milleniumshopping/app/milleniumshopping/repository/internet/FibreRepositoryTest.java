package com.milleniumshopping.app.milleniumshopping.repository.internet;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.internet.Fibre;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.FibreRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class FibreRepositoryTest extends AndroidTestCase{
    private static final String TAG = "FIBRE TEST";

    public void createReadUpdateDeleteTest() throws Exception
    {
        FibreRepository fibreRepository = new FibreRepositoryImpl(this.getContext());
        String ipAddress;

        //Create
        Fibre createdInternet = new Fibre.Builder()
                .ipAddress("10.1.1.1")
                .ISP("Telkom")
                .planName("Fibre")
                .price("R5699")
                .dataAllowance("100GB")
                .type("Fibre")
                .build();

        Fibre insertedInternet = fibreRepository.save(createdInternet);
        ipAddress = insertedInternet.getIPAddress();
        Assert.assertNotNull(TAG + " CREATE", insertedInternet);

        //Read all
        Set<Fibre> internetServices = fibreRepository.findAll();
        Assert.assertTrue(TAG + " READ ALL", internetServices.size() > 0);

        //Read entity
        Fibre internet = fibreRepository.findById(createdInternet.getIPAddress(), createdInternet.getType());
        Assert.assertNotNull(TAG + " READ FIBRE", internet);

        //Update internet
        Fibre updateInternet = new Fibre.Builder()
                .copy(internet)
                .ipAddress("NewIP")
                .build();
        fibreRepository.update(updateInternet);
        Fibre newInternet = fibreRepository.findById(updateInternet.getIPAddress(), updateInternet.getType());
        Assert.assertEquals(TAG + " UPDATE FIBRE", "NewIP", newInternet.getIPAddress());

        //Delete employee
        fibreRepository.delete(updateInternet);
        Fibre deletedInternet = fibreRepository.findById(updateInternet.getIPAddress(), updateInternet.getType());
        Assert.assertNull(TAG + " DELETE", deletedInternet);
    }
}
