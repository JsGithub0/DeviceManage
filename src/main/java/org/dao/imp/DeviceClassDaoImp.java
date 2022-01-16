package org.dao.imp;
import java.util.List;
import org.dao.DeviceClassDao;
import org.model.Deviceclass;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DeviceClassDaoImp extends HibernateDaoSupport implements DeviceClassDao
{
	public List<Deviceclass> findAllDeviceClass() {
		List<Deviceclass> list = getHibernateTemplate().find("from org.model.Deviceclass"); 
		return list;
	}
	public Deviceclass findDeviceClass(int deviceClassId) {
		List<Deviceclass> list = getHibernateTemplate().find("from org.model.Deviceclass where deviceClassId=?", deviceClassId);
		Deviceclass dc = list.get(0);
 		return dc;
	}

	@Override
	public void deleteDeviceClass(Deviceclass deviceclass) {
		getHibernateTemplate().delete(deviceclass);
	}

	@Override
	public void addDeviceClass(Deviceclass deviceclass) {
        getHibernateTemplate().save(deviceclass);
	}

	@Override
	public void updateDeviceClass(Deviceclass deviceclass) {
        getHibernateTemplate().update(deviceclass);
	}
}
