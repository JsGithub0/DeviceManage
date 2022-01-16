package app.action.imp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.UserDao;
import org.model.Information;
import org.model.User;

import app.action.UserAction;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class UserActionImp extends ActionSupport implements UserAction,
		ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;	
	// �ص���������tomcat�������˷�����Ӧ���ƶ���ʱ���Զ����� 
	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}
	// �ص����������ƶ�����tomcat�������˷�������ʱ���Զ����� 
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void makeJson(List<User> list) throws IOException{
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// ����json����ַ���
		JSONArray jsonArray = new JSONArray();
		for(User user: list){
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("UserID", user.getUserId());
			jsonObj.put("UserName", user.getUserName());
			jsonObj.put("UserPassword", user.getUserPassword());
			jsonArray.add(jsonObj);
		}
		System.out.println(jsonArray.toString());
		JSONObject root = new JSONObject();
		root.put("result", jsonArray);
		out.write(root.toString());
		out.flush();
		out.close();
	}	
	
	public void findAllUser() throws IOException {
		List<User> list = userDao.findAllUser();
		makeJson(list);
	}

	public void findUserById() throws IOException {
		String id = request.getParameter("userId");		
		User user = userDao.findUserById(new Integer(id));
		List<User> list = new ArrayList<User>();
		list.add(user);		
		makeJson(list);
	}
	public void loginValidate() throws IOException {
		String username = request.getParameter("username");	
		String password = request.getParameter("password");
		User user = userDao.loginValidate(username, password);
		List<User> list = new ArrayList<User>();
		list.add(user);		
		makeJson(list);
	}


}
