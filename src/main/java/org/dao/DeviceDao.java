package org.dao;
import java.util.List;
import org.model.Device;

public interface DeviceDao {
	// ��������豸������б�
	List<Device> findAllDevice();
	// ���ָ���豸�����ŵ������豸������б�
	List<Device> findDeviceByDeviceClassId (int deviceClassId);
	// ���ָ���豸��ŵ��豸����
	Device findDeviceById(int deviceId);	
    // ���ָ���豸���Ƶ������豸������б�
	List<Device> findDeviceByName(String deviceName);
	// ���ָ���豸�۸�Χ�������豸������б�
	List<Device> findDeviceByPrice(String low, String high);
	// ���ָ���豸���������豸���ƺͼ۸�Χ�������豸������б�
	List<Device> findDeviceByFuzzy(String deviceClassName, String deviceName, String low, String high);
	// �޸��豸����
	void updateDeviceById(int deviceId, String deviceName, String devicePrice);
	void updateDevice(Device device);
	void deleteDevice(Device device);
	void addDevice(Device device);
}
