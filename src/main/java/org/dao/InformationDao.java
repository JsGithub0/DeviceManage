package org.dao;
import java.util.List;
import org.model.Information;

public interface InformationDao {
	// 新增咨询对象
	public void addInformation(Information info);
	// 获得所有咨询对象的列表
	public List<Information> findAllInformation();
	// 获得指定咨询编号的咨询对象
	public Information findInformationById(int infoId);
}
