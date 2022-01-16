package app.action.imp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.ShopingcartDao;
import org.dao.ShopingorderDao;
import org.dao.UserDao;
import org.model.Device;
import org.model.Shopingcart;
import org.model.Shopingorder;
import org.model.User;

import app.action.ShopingorderAction;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class ShopingorderActionImp extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ShopingorderAction {
	private HttpServletRequest request;
	private HttpServletResponse response;	
	// 回调函数，当tomcat服务器端发送响应给移动端时，自动调用 
	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}
	// 回调函数，当移动端向tomcat服务器端发送请求时，自动调用 
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}
	
	private ShopingorderDao shopingorderDao;
	public void setShopingorderDao(ShopingorderDao shopingorderDao) {
		this.shopingorderDao = shopingorderDao;
	}
	
	public void makeJson(List<Shopingorder> list) throws IOException{
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 构造json输出字符串
		JSONArray jsonArray = new JSONArray();
		for(Shopingorder shopingorder: list){
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("ShopingOrderID", new Integer(shopingorder.getShopingOrderId().toString()));
			jsonObj.put("UserId", shopingorder.getUser().getUserId());
			jsonObj.put("Receiver", shopingorder.getReceiver());
			jsonObj.put("ReceiveAddress", shopingorder.getReceiveAddress());
			jsonObj.put("Createtime", shopingorder.getCreatetime());
			jsonObj.put("MoneyAmount", shopingorder.getMoneyAmount());			
			jsonArray.add(jsonObj);
		}
		System.out.println(jsonArray.toString());
		JSONObject root = new JSONObject();
		root.put("result", jsonArray);
		out.write(root.toString());
		out.flush();
		out.close();
	}
	
		
	public void findAllShopingorder() throws IOException {
		List<Shopingorder> list = shopingorderDao.findAllShopingorder();
		makeJson(list);
	}

	public void findShopingorderById() throws IOException {
		String id = request.getParameter("shopingorderId");		
		Shopingorder shopingorder = shopingorderDao.findShopingorderById(new Integer(id));
		List<Shopingorder> list = new ArrayList<Shopingorder>();
		list.add(shopingorder);
		
		makeJson(list);	
	}

	public void findShopingorderByUserId() throws IOException {
		String id = request.getParameter("userId");	
		List<Shopingorder> list = shopingorderDao.findShopingorderByUserId(new Integer(id));
		makeJson(list);
	}

	private UserDao userDao;	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String addShopingorder() throws IOException {
		// 由于主键是自增的，因此不需要传入shopingOrderID编号
		//String shopingOrderID = request.getParameter("shopingOrderID");
		String userId = request.getParameter("userId");
		String receiver = request.getParameter("receiver");
		String receiveAddress = request.getParameter("receiveAddress");
		String createtime = request.getParameter("createtime");
		String moneyAmount = request.getParameter("moneyAmount");
		
		Shopingorder so = new Shopingorder();
		//so.setShopingOrderId(new Integer(shopingOrderID));
		User user = userDao.findUserById(new Integer(userId));
		so.setUser(user);
		so.setReceiver(receiver);
		so.setReceiveAddress(receiveAddress);
		so.setCreatetime(createtime);
		so.setMoneyAmount(moneyAmount);		
		
		shopingorderDao.addShopingorder(so);
		return "success";
	}

	public String deleteShopingorder() throws IOException {
		String id = request.getParameter("deleteShopingorderID");
		Shopingorder so = shopingorderDao.findShopingorderById(new Integer(id));
		shopingorderDao.deleteShopingorder(so);
		
		return "success";
	}
}
