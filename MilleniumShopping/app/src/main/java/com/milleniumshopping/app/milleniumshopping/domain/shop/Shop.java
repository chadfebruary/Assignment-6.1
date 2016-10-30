package com.milleniumshopping.app.milleniumshopping.domain.shop;

import java.io.Serializable;

/**
 * Created by Admin on 2016/04/15.
 */
public class Shop implements Serializable{
    private String shopNumber, shopName, shopOwner, shopPhoneNumber;

    private Shop(){}

    private Shop(Builder builder)
    {
        this.shopNumber = builder.shopNumber;
        this.shopName = builder.shopName;
        this.shopOwner = builder.shopOwner;
        this.shopPhoneNumber = builder.shopPhoneNumber;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopOwner() {
        return shopOwner;
    }

    public String getShopPhoneNumber() {
        return shopPhoneNumber;
    }


    public static class Builder
    {
        private String shopNumber, shopName, shopOwner, shopPhoneNumber;

        public Builder shopNumber(String value)
        {
            this.shopNumber = value;
            return this;
        }

        public Builder shopName(String value)
        {
            this.shopName = value;
            return this;
        }

        public Builder shopOwner(String value)
        {
            this.shopOwner = value;
            return this;
        }

        public Builder shopPhoneNumber(String value)
        {
            this.shopPhoneNumber = value;
            return this;
        }

        public Builder copy(Shop value)
        {
            this.shopNumber = value.shopNumber;
            this.shopName = value.shopName;
            this.shopOwner = value.shopOwner;
            this.shopPhoneNumber = value.shopPhoneNumber;

            return this;
        }

        public Shop build()
        {
            return new Shop(this);
        }

        public static Builder builder()
        {
            return new Builder();
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;

        if(o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        return shopNumber != null ? shopNumber.equals(shop.shopNumber) : shop.shopNumber == null;
    }

    @Override
    public int hashCode()
    {
        return shopNumber != null ? shopNumber.hashCode() : 0;
    }

}
