package org.dao.imp;
import java.util.List;
import org.dao.ShopingcartDao;
import org.dao.ShopingorderDao;
import org.dao.ShopingorderitemDao;
import org.model.Device;
import org.model.Shopingcart;
import org.model.Shopingorder;
import org.model.Shopingorderitem;
import org.model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ShopingcartDaoImp extends HibernateDaoSupport implements
		ShopingcartDao {
	public List<Shopingcart> findAllShopingcart() {
		List<Shopingcart> list = getHibernateTemplate().find("from org.model.Shopingcart"); 
		return list;
	}
	public Shopingcart findShopingcartById(int ShopingcartId) {
		List<Shopingcart> list = getHibernateTemplate().find("from org.model.Shopingcart where shopingcartId=?", ShopingcartId);
		Shopingcart shopingcart = list.get(0);
		return shopingcart;
	}
	public void addShopingcart(Shopingcart Shopingcart) {
		getHibernateTemplate().save(Shopingcart);
	}
	public void deleteShopingcart(Shopingcart Shopingcart) {
		getHibernateTemplate().delete(Shopingcart);
	}
	public List<Shopingcart> findAllShopingcartByUserId(int userId) {
		List<Shopingcart> list = getHibernateTemplate().find("from org.model.Shopingcart where UserID=?", userId); 
		return list;
	}
	/*
	 ��ѡ�����ɸ��豸����4�������н���ʱ������ɣ�
	�� �������в���1����¼
	�� ��������в���4����¼
	�� ���ﳵ����ɾ��4����¼
	 */	
	private ShopingorderDao shopingorderDao;	
	public void setShopingorderDao(ShopingorderDao shopingorderDao) {
		this.shopingorderDao = shopingorderDao;
	}
	
	private ShopingorderitemDao shopingorderitemDao;	
	public void setShopingorderitemDao(ShopingorderitemDao shopingorderitemDao) {
		this.shopingorderitemDao = shopingorderitemDao;
	}
	
	public void jiesuanShopingcart(Shopingorder so,	List<Shopingcart> shopingcartList) {
		// �� �������в���1����¼
		shopingorderDao.addShopingorder(so);
		// �� ��������в���4����¼
		for(Shopingcart sc:shopingcartList){
			Shopingorderitem soi = new Shopingorderitem();
			// soid��setShopingOrderItemId����Ҫ����Ϊ��������			
			soi.setShopingorder(so);
			Device dev = sc.getDevice();
			soi.setDevice(dev);
			int buyNum = sc.getBuyNum();
			soi.setBuyNum(buyNum);
			shopingorderitemDao.addShopingorderitem(soi);
		}
		// �� ���ﳵ����ɾ��4����¼
		for(Shopingcart sc:shopingcartList){
			deleteShopingcart(sc);
		}		
	}

	@Override
	public void updateShopingcart(Shopingcart shopingcart) {
		getHibernateTemplate().update(shopingcart);
	}
}
