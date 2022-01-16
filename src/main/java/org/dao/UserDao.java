package org.dao;
import java.util.List;
import org.model.User;

public interface UserDao {
	// ��������û�������б�
	public List<User> findAllUser();
	// ���ָ���û���ŵ��û�����
	public User findUserById(int userId);
	// ��֤�û�
	public User loginValidate(String username, String password);
	public void addUser(User user);
	public void deleteUser(User user);
	public void updateUser(User user);
}
