package org.dao.imp;
import java.util.List;
import org.dao.InformationDao;
import org.model.Device;
import org.model.Information;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class InformationDaoImp extends HibernateDaoSupport implements
		InformationDao {
	public void addInformation(Information info) {
		getHibernateTemplate().save(info);
	}

	public List<Information> findAllInformation() {
		List<Information> list = getHibernateTemplate().find("from org.model.Information"); 
		return list;
	}

	public Information findInformationById(int infoId) {
		List<Information> list = getHibernateTemplate().find("from org.model.Information where InformationID=?", infoId); 
		Information info = list.get(0);
		return info;
	}
}
