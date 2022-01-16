package org.dao.imp;
import java.util.ArrayList;
import java.util.List;

import org.dao.DeviceDao;
import org.model.Device;
import org.model.Deviceclass;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DeviceDaoImp extends HibernateDaoSupport implements DeviceDao {
	public List<Device> findAllDevice() {
		List<Device> list = getHibernateTemplate().find("from org.model.Device"); 
		return list;
	}
	public List<Device> findDeviceByDeviceClassId(int deviceClassId) {
		List<Device> list = getHibernateTemplate().find("from org.model.Device where deviceclass.deviceClassId=?", deviceClassId);
		return list;
	}
	public Device findDeviceById(int deviceId) {
		List<Device> list = getHibernateTemplate().find("from org.model.Device where deviceId=?", deviceId); 
		Device dev = list.get(0);
		return dev;
	}
	public List<Device> findDeviceByName(String deviceName) {
		List<Device> list = getHibernateTemplate().find("from org.model.Device where deviceName like '%" + deviceName + "%'"); 
        /* ע�⣺����2��д��������Ч��
        1. List<Device> list = getHibernateTemplate().find("from org.model.Device where deviceName like ?", "'%" + deviceName + "%'"); 
		2. List<Device> list = getHibernateTemplate().find("from org.model.Device where deviceName like '%?%'", deviceName); 
        */
		return list;
	}
	public List<Device> findDeviceByPrice(String low, String high) {
		List<Device> list = getHibernateTemplate().find("from org.model.Device where devicePrice between " + low + " and " + high);
		return list;
	}
	/*	ģ����ѯ��������3���������豸�б�
	   	����1���豸�������ư��������칫��
		����2���豸���ư���������ӡ��
		����3���豸�۸�Χ��1000��3000֮��
	*/
	public List<Device> findDeviceByFuzzy(String deviceClassName,
			String deviceName, String low, String high) {
		// ����Device���2�����������Ԥ��ѯ�����preList
		String hql = "from Device where deviceName like '%" + deviceName
				+ "%' and devicePrice between " + low + " and " + high;
		List<Device> preList = getHibernateTemplate().find(hql);
		// �������ս����finalList
		List<Device> finalList = new ArrayList<Device>();
		for (Device d : preList) {
			// ����DeviceClass���1��������ɸѡԤ��ѯ�����preList
			String hql1 = "from Deviceclass where deviceClassId=" + d.getDeviceclass().getDeviceClassId()
						+ " and deviceClassName like '%" + deviceClassName + "%'";
			List<Deviceclass> dclist = getHibernateTemplate().find(hql1);
			d.setDeviceclass(dclist.get(0));
			// ������3��������Device�������finalList
			if(dclist != null){
				finalList.add(d);
			}
		}		
		return finalList;
	}
	
	public void updateDeviceById(int deviceId, String deviceName,
			String devicePrice) {
		Device dev = findDeviceById(deviceId);
		dev.setDeviceName(deviceName);
		dev.setDevicePrice(devicePrice);
		getHibernateTemplate().update(dev);
	}

	@Override
	public void updateDevice(Device device) {
		getHibernateTemplate().update(device);
	}

	@Override
	public void deleteDevice(Device device) {
        getHibernateTemplate().delete(device);
	}

	@Override
	public void addDevice(Device device) {
        getHibernateTemplate().save(device);
	}
}
