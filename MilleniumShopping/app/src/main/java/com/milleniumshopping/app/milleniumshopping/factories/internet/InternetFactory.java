package com.milleniumshopping.app.milleniumshopping.factories.internet;

import com.milleniumshopping.app.milleniumshopping.domain.internet.Internet;

import java.io.Serializable;

/**
 * Write a description of class InternetFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InternetFactory implements Serializable
{
    public static Internet getInternetType(String ipAddress, String type, String ISP, String planName, String price, String dataAllowance)
    {
            Internet internet = new Internet.Builder()
                    .ipAddress(ipAddress)
                    .ISP(ISP)
                    .planName(planName)
                    .price(price)
                    .dataAllowance(dataAllowance)
                    .type(type)
                    .build();

            return internet;
    }
}
