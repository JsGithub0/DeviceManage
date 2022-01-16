package org.model;

import java.util.Set;

public class User {
    private Integer userId;
    private String userName;
    private String userPassword;
    private Set<Shopingcart> shopingcarts;
    private Set<Shopingorder> shopingorders;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public Set<Shopingcart> getShopingcarts() {
        return shopingcarts;
    }

    public void setShopingcarts(Set<Shopingcart> shopingcarts) {
        this.shopingcarts = shopingcarts;
    }

    public Set<Shopingorder> getShopingorders() {
        return shopingorders;
    }

    public void setShopingorders(Set<Shopingorder> shopingorders) {
        this.shopingorders = shopingorders;
    }
}
