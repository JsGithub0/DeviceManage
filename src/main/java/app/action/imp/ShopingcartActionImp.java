package app.action.imp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.DeviceDao;
import org.dao.ShopingcartDao;
import org.dao.ShopingorderDao;
import org.dao.UserDao;
import org.model.Device;
import org.model.Information;
import org.model.Shopingcart;
import org.model.Shopingorder;
import org.model.Shopingorderitem;
import org.model.User;

import app.action.ShopingcartAction;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class ShopingcartActionImp extends ActionSupport implements
		ShopingcartAction, ServletRequestAware, ServletResponseAware {
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
	
	private ShopingcartDao shopingcartDao;
	public void setShopingcartDao(ShopingcartDao shopingcartDao) {
		this.shopingcartDao = shopingcartDao;
	}
	
	private DeviceDao deviceDao;	
	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}
	
	public void makeJson(List<Shopingcart> list) throws IOException{
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// ����json����ַ���
		JSONArray jsonArray = new JSONArray();
		for(Shopingcart shopingcart: list){
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("ShopingcartID", shopingcart.getShopingcartId());
			// ���ڹ��ﳵ����Ҫ֪���豸�ĵ��ۣ������Ҫ��Device�����ȫ�����Զ���json���
			Integer deviceId = shopingcart.getDevice().getDeviceId();
			Device dev = deviceDao.findDeviceById(new Integer(deviceId));
			JSONObject jsonObjdev = new JSONObject();
			jsonObjdev.put("DeviceID", dev.getDeviceId());
			jsonObjdev.put("DeviceClassId", dev.getDeviceclass()
					.getDeviceClassId());
			jsonObjdev.put("DeviceName", dev.getDeviceName());
			jsonObjdev.put("DevicePrice", dev.getDevicePrice());
			jsonObj.put("Device", jsonObjdev);
			
			jsonObj.put("BuyNum", shopingcart.getBuyNum());
			jsonObj.put("UserID", shopingcart.getUser().getUserId());
			jsonArray.add(jsonObj);
		}
		System.out.println(jsonArray.toString());
		JSONObject root = new JSONObject();
		root.put("result", jsonArray);
		out.write(root.toString());
		out.flush();
		out.close();
	}

	
	public void findAllShopingcart() throws IOException {
		List<Shopingcart> list = shopingcartDao.findAllShopingcart();
		makeJson(list);
	}

	public void findShopingcartById() throws IOException {
		String id = request.getParameter("shopingcartId");		
		Shopingcart shopingcart = shopingcartDao.findShopingcartById(new Integer(id));
		List<Shopingcart> list = new ArrayList<Shopingcart>();
		list.add(shopingcart);
		
		makeJson(list);	
	}
	
	
	private UserDao userDao;	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String addShopingcart() throws IOException {
		// ���������������ģ���˲���Ҫ����addShopingcartID���
		//String shopingcartId = request.getParameter("addShopingcartID");
		String deviceId = request.getParameter("addDeviceID");
		String buyNum = request.getParameter("addBuyNum");
		String userId = request.getParameter("addUserID");
		Shopingcart sc = new Shopingcart();
		//sc.setShopingcartId(new Integer(shopingcartId));
		Device dev = deviceDao.findDeviceById(new Integer(deviceId));
		sc.setDevice(dev);
		sc.setBuyNum(new Integer(buyNum));
		User user = userDao.findUserById(new Integer(userId));
		sc.setUser(user);
		
		shopingcartDao.addShopingcart(sc);		
		return "success";
	}

	public String deleteShopingcart() throws IOException {
		String shopingcartId = request.getParameter("deleteShopingcartID");
		Shopingcart sc = shopingcartDao.findShopingcartById(new Integer(shopingcartId));
		shopingcartDao.deleteShopingcart(sc);
		
		return "success";
	}
	
	public void findAllShopingcartByUserId() throws IOException {
		String id = request.getParameter("userId");	
		List<Shopingcart> list = new ArrayList<Shopingcart>();
		list = shopingcartDao.findAllShopingcartByUserId(new Integer(id));
		
		makeJson(list);	
	}
	
	public String jiesuanShopingcart() {
		// 1. ����Shopingorder����
		Shopingorder so = new Shopingorder();
		String userId = request.getParameter("userId");	
		User user = userDao.findUserById(new Integer(userId));
		so.setUser(user);
		String receiver = request.getParameter("receiver");	
		so.setReceiver(receiver);
		String receiveAddress = request.getParameter("receiveAddress");	
		so.setReceiveAddress(receiveAddress);
		String createtime = request.getParameter("createtime");	 
		so.setCreatetime(createtime);
		String moneyAmount = request.getParameter("moneyAmount");
		so.setMoneyAmount(moneyAmount);
		// 2. ����Shopingcart�б����
		List<Shopingcart> shopingcartList = new ArrayList<Shopingcart>();
		/* app�˽�����shopingcartId�������ַ������м��ö��ŷָ�
		         ���磺1,3,4,6��ʾѡ����1,3,4,6�Ź��ﳵ*/
		String shopingcartIds = request.getParameter("shopingcartIds");	
		String[] shopingcartIdList = shopingcartIds.split(",");
		for(String shopingcartId: shopingcartIdList){
			Shopingcart sc = shopingcartDao.findShopingcartById(new Integer(shopingcartId));
			shopingcartList.add(sc);
		}
		shopingcartDao.jiesuanShopingcart(so, shopingcartList);
		return "success";
	}
}
