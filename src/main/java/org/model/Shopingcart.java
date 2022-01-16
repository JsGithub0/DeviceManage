package org.model;

public class Shopingcart {
    private Integer shopingcartId;
    private Integer buyNum;
    private Device device;
    private User user;

    public Integer getShopingcartId() {
        return shopingcartId;
    }

    public void setShopingcartId(Integer shopingcartId) {
        this.shopingcartId = shopingcartId;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }


    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
