package org.dao;
import java.util.List;
import org.model.Shopingorder;

public interface ShopingorderDao {
	// ������ж���������б�
	public List<Shopingorder> findAllShopingorder();
	// ���ָ��������ŵĶ�������
	public Shopingorder findShopingorderById(int shopingorderId);
	// �������ָ���û���ŵĶ�������
	public List<Shopingorder> findShopingorderByUserId(int userId);
	// ����һ����������
	public void addShopingorder(Shopingorder shopingorder);
	// ɾ��һ����������
	public void deleteShopingorder(Shopingorder shopingorder);
	public void updateShopingorder(Shopingorder shopingorder);
}
