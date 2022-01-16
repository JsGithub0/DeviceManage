package org.dao;
import java.util.List;
import org.model.Deviceclass;

public interface DeviceClassDao {
	// ��������豸���������б�
	List<Deviceclass> findAllDeviceClass();
	// ���ָ����ŵ��豸�������
	Deviceclass findDeviceClass(int deviceClassId);
	void deleteDeviceClass(Deviceclass deviceclass);
	void addDeviceClass(Deviceclass deviceclass);
	void updateDeviceClass(Deviceclass deviceclass);

}
