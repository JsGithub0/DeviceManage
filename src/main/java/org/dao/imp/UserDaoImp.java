package org.dao.imp;
import java.util.List;

import org.dao.UserDao;
import org.model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDaoImp extends HibernateDaoSupport implements UserDao {
	public List<User> findAllUser() {
		List<User> list = getHibernateTemplate().find("from org.model.User"); 
		return list;
	}
	public User findUserById(int userId) {
		List<User> list = getHibernateTemplate().find("from org.model.User where UserID=?", userId); 
		User user = list.get(0);
		return user;
	}
	public User loginValidate(String username, String password) {
		String strs[] = {username, password};
		List<User> list = getHibernateTemplate().find("from org.model.User where UserName=? and UserPassword=?", strs); 
		User user = list.get(0);
		return user;
	}

	@Override
	public void addUser(User user) {
		getHibernateTemplate().save(user);
	}

	@Override
	public void deleteUser(User user) {
        getHibernateTemplate().delete(user);
	}

	@Override
	public void updateUser(User user) {
        getHibernateTemplate().update(user);
	}

}
