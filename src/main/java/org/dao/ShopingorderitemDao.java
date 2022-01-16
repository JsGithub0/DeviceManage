package org.dao;
import java.util.List;
import org.model.Shopingorderitem;

public interface ShopingorderitemDao {
	// ������ж����������б�
	public List<Shopingorderitem> findAllShopingorderitem();
	// ���ָ���������ŵĶ��������
	public Shopingorderitem findShopingorderitemById(int shopingorderitemId);
	// ���ָ��������ŵĶ������б�
	public List<Shopingorderitem> findShopingorderitemListByShopingorderId(int shopingorderId);
	// ����һ�����������
	public void addShopingorderitem(Shopingorderitem shopingorderitem);
	// ɾ��һ�����������
	public void deleteShopingorderitem(Shopingorderitem shopingorderitem);
	public void updateShopingorderitem(Shopingorderitem shopingorderitem);
}
