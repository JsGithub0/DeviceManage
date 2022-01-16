package org.dao;
import java.util.List;
import org.model.Device;

public interface DeviceDao {
	// 获得所有设备对象的列表
	List<Device> findAllDevice();
	// 获得指定设备分类编号的所有设备对象的列表
	List<Device> findDeviceByDeviceClassId (int deviceClassId);
	// 获得指定设备编号的设备对象
	Device findDeviceById(int deviceId);	
    // 获得指定设备名称的所有设备对象的列表
	List<Device> findDeviceByName(String deviceName);
	// 获得指定设备价格范围的所有设备对象的列表
	List<Device> findDeviceByPrice(String low, String high);
	// 获得指定设备分类名、设备名称和价格范围的所有设备对象的列表
	List<Device> findDeviceByFuzzy(String deviceClassName, String deviceName, String low, String high);
	// 修改设备对象
	void updateDeviceById(int deviceId, String deviceName, String devicePrice);
	void updateDevice(Device device);
	void deleteDevice(Device device);
	void addDevice(Device device);
}
