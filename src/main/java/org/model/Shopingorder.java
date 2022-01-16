package org.model;

import java.util.Set;

public class Shopingorder {
    private Integer shopingOrderId;
    private String receiver;
    private String receiveAddress;
    private String createtime;
    private String moneyAmount;
    private User user;
    private Set<Shopingorderitem> shopingorderitems;

    public Integer getShopingOrderId() {
        return shopingOrderId;
    }

    public void setShopingOrderId(Integer shopingOrderId) {
        this.shopingOrderId = shopingOrderId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(String moneyAmount) {
        this.moneyAmount = moneyAmount;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Shopingorderitem> getShopingorderitems() {
        return shopingorderitems;
    }

    public void setShopingorderitems(Set<Shopingorderitem> shopingorderitems) {
        this.shopingorderitems = shopingorderitems;
    }
}
