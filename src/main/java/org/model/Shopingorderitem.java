package org.model;

public class Shopingorderitem {
    private Integer shopingOrderItemId;
    private Integer buyNum;
    private Shopingorder shopingorder;
    private Device device;

    public Integer getShopingOrderItemId() {
        return shopingOrderItemId;
    }

    public void setShopingOrderItemId(Integer shopingOrderItemId) {
        this.shopingOrderItemId = shopingOrderItemId;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }



    public Shopingorder getShopingorder() {
        return shopingorder;
    }

    public void setShopingorder(Shopingorder shopingorder) {
        this.shopingorder = shopingorder;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
