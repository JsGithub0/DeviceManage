package org.dao;
import java.util.List;
import org.model.Shopingcart;
import org.model.Shopingorder;

public interface ShopingcartDao {
	// ������й��ﳵ������б�
	public List<Shopingcart> findAllShopingcart();
	// ���ָ�����ﳵ��ŵĹ��ﳵ����
	public Shopingcart findShopingcartById(int ShopingcartId);
	// ����һ�����ﳵ����
	public void addShopingcart(Shopingcart Shopingcart);
	// ɾ��һ�����ﳵ����
	public void deleteShopingcart(Shopingcart Shopingcart);
	// ���ָ���û���ŵ����й��ﳵ������б�
	public List<Shopingcart> findAllShopingcartByUserId(int userId);
	// ���㹺�ﳵ
	public void jiesuanShopingcart(Shopingorder so, List<Shopingcart> shopingcartList);
	public void updateShopingcart(Shopingcart shopingcart);
}
