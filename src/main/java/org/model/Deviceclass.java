package org.model;

import java.util.Set;

public class Deviceclass {
    private Integer deviceClassId;
    private String deviceClassName;
    private Set<Device> devices;

    public Integer getDeviceClassId() {
        return deviceClassId;
    }

    public void setDeviceClassId(Integer deviceClassId) {
        this.deviceClassId = deviceClassId;
    }

    public String getDeviceClassName() {
        return deviceClassName;
    }

    public void setDeviceClassName(String deviceClassName) {
        this.deviceClassName = deviceClassName;
    }


    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }
}
