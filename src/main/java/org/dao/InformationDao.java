package org.dao;
import java.util.List;
import org.model.Information;

public interface InformationDao {
	// ������ѯ����
	public void addInformation(Information info);
	// ���������ѯ������б�
	public List<Information> findAllInformation();
	// ���ָ����ѯ��ŵ���ѯ����
	public Information findInformationById(int infoId);
}
