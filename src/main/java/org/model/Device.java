package org.model;

import java.util.Set;

public class Device {
    private Integer deviceId;
    private String deviceName;
    private String devicePrice;
    private Deviceclass deviceclass;
    private Set<Shopingcart> shopingcarts;
    private Set<Shopingorderitem> shopingorderitems;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDevicePrice() {
        return devicePrice;
    }

    public void setDevicePrice(String devicePrice) {
        this.devicePrice = devicePrice;
    }

    public Deviceclass getDeviceclass() {
        return deviceclass;
    }

    public void setDeviceclass(Deviceclass deviceclass) {
        this.deviceclass = deviceclass;
    }

    public Set<Shopingcart> getShopingcarts() {
        return shopingcarts;
    }

    public void setShopingcarts(Set<Shopingcart> shopingcarts) {
        this.shopingcarts = shopingcarts;
    }

    public Set<Shopingorderitem> getShopingorderitems() {
        return shopingorderitems;
    }

    public void setShopingorderitems(Set<Shopingorderitem> shopingorderitems) {
        this.shopingorderitems = shopingorderitems;
    }
}
