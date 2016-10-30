package com.milleniumshopping.app.milleniumshopping.domain.shop;

import java.io.Serializable;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ShoppingCentre implements Serializable{
    private String centreName, centreCode, portfolio, address;

    private ShoppingCentre(){}

    private ShoppingCentre(Builder builder)
    {
        this.centreName = builder.centreName;
        this.centreCode = builder.centreCode;
        this.portfolio = builder.portfolio;
        this.address = builder.address;
    }

    public String getCentreName() {
        return centreName;
    }

    public String getCentreCode() {
        return centreCode;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public String getAddress() {
        return address;
    }


    public static class Builder
    {
        private String centreName, centreCode, portfolio, address;

        public Builder centreName(String value)
        {
            this.centreName = value;
            return this;
        }

        public Builder centreCode(String value)
        {
            this.centreCode = value;
            return this;
        }

        public Builder portfolio(String value)
        {
            this.portfolio = value;
            return this;
        }

        public Builder address(String value)
        {
            this.address = value;
            return this;
        }

        public Builder copy(ShoppingCentre value)
        {
            this.centreName = value.centreName;
            this.centreCode = value.centreCode;
            this.portfolio = value.portfolio;
            this.address = value.address;

            return this;
        }

        public ShoppingCentre build()
        {
            return new ShoppingCentre(this);
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

        ShoppingCentre shop = (ShoppingCentre) o;

        return centreName != null ? centreName.equals(shop.centreName) : shop.centreName == null;
    }

    @Override
    public int hashCode()
    {
        return centreName != null ? centreName.hashCode() : 0;
    }
}
