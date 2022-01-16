package org.dao.imp;
import java.util.List;
import org.dao.ShopingorderitemDao;
import org.model.Shopingorderitem;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ShopingorderitemDaoImp extends HibernateDaoSupport implements
		ShopingorderitemDao {
	public List<Shopingorderitem> findAllShopingorderitem() {
		List<Shopingorderitem> list = getHibernateTemplate().find("from org.model.Shopingorderitem"); 
		return list;
	}
	public Shopingorderitem findShopingorderitemById(int shopingorderitemId) {
		List<Shopingorderitem> list = getHibernateTemplate().find("from org.model.Shopingorderitem where ShopingOrderItemID=?", shopingorderitemId); 
		Shopingorderitem shopingorderitem = list.get(0);
		return shopingorderitem;
	}
	public List<Shopingorderitem> findShopingorderitemListByShopingorderId(
			int shopingorderId) {
		List<Shopingorderitem> list = getHibernateTemplate().find("from org.model.Shopingorderitem where ShopingOrderID=?", shopingorderId); 
		return list;
	}
	public void addShopingorderitem(Shopingorderitem shopingorderitem) {
		getHibernateTemplate().save(shopingorderitem);
	}
	public void deleteShopingorderitem(Shopingorderitem shopingorderitem) {
		getHibernateTemplate().delete(shopingorderitem);
	}

	@Override
	public void updateShopingorderitem(Shopingorderitem shopingorderitem) {
		getHibernateTemplate().update(shopingorderitem);
	}
}
