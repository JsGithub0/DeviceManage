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
import org.dao.ShopingorderDao;
import org.dao.ShopingorderitemDao;
import org.model.Device;
import org.model.Shopingcart;
import org.model.Shopingorder;
import org.model.Shopingorderitem;
import org.model.User;

import app.action.ShopingorderitemAction;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class ShopingorderitemActionImp extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ShopingorderitemAction {
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
	
	private ShopingorderitemDao shopingorderitemDao;
	public void setShopingorderitemDao(ShopingorderitemDao shopingorderitemDao) {
		this.shopingorderitemDao = shopingorderitemDao;
	}
	
	private DeviceDao deviceDao;	
	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}
	
	public void makeJson(List<Shopingorderitem> list) throws IOException{
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 构造json输出字符串
		JSONArray jsonArray = new JSONArray();
		for(Shopingorderitem soi: list){
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("ShopingOrderItemID", soi.getShopingOrderItemId());
			jsonObj.put("ShopingOrderID", soi.getShopingorder().getShopingOrderId());
			
			// 由于订单项需要知道设备的单价，因此需要将Device对象的全部属性都做json输出
			Integer deviceId = soi.getDevice().getDeviceId();
			Device dev = deviceDao.findDeviceById(new Integer(deviceId));
			JSONObject jsonObjdev = new JSONObject();
			jsonObjdev.put("DeviceID", dev.getDeviceId());
			jsonObjdev.put("DeviceClassId", dev.getDeviceclass()
								.getDeviceClassId());
			jsonObjdev.put("DeviceName", dev.getDeviceName());
			jsonObjdev.put("DevicePrice", dev.getDevicePrice());
			jsonObj.put("Device", jsonObjdev);
			
			jsonObj.put("BuyNum", soi.getBuyNum());
			jsonArray.add(jsonObj);
		}
		System.out.println(jsonArray.toString());
		JSONObject root = new JSONObject();
		root.put("result", jsonArray);
		out.write(root.toString());
		out.flush();
		out.close();
	}

	
	public void findAllShopingorderitem() throws IOException {
		List<Shopingorderitem> list = shopingorderitemDao.findAllShopingorderitem();
		makeJson(list);
	}

	public void findShopingorderitemById() throws IOException {
		String id = request.getParameter("shopingorderitemId");		
		Shopingorderitem shopingorderitem = shopingorderitemDao.findShopingorderitemById(new Integer(id));
		List<Shopingorderitem> list = new ArrayList<Shopingorderitem>();
		list.add(shopingorderitem);		
		makeJson(list);	
	}

	public void findShopingorderitemListByShopingorderId() throws IOException {
		String id = request.getParameter("shopingorderId");		
		List<Shopingorderitem> list =  shopingorderitemDao.findShopingorderitemListByShopingorderId(new Integer(id));
		makeJson(list);
	}
	
	private ShopingorderDao shopingorderDao;
	public void setShopingorderDao(ShopingorderDao shopingorderDao) {
		this.shopingorderDao = shopingorderDao;
	}	
	
	public String addShopingorderitem() throws IOException {
		// 由于主键是自增的，因此不需要传入shopingOrderItemID编号
		//String shopingOrderItemID = request.getParameter("shopingOrderItemID");
		String shopingOrderID = request.getParameter("shopingOrderID");
		String deviceID = request.getParameter("deviceID");
		String buyNum = request.getParameter("buyNum");
		
		Shopingorderitem soi = new Shopingorderitem();
		//soi.setShopingOrderItemId(new Integer(shopingOrderItemID));		
		Shopingorder so = shopingorderDao.findShopingorderById(new Integer(shopingOrderID));
		soi.setShopingorder(so);
		Device dev = deviceDao.findDeviceById(new Integer(deviceID));
		soi.setDevice(dev);
		soi.setBuyNum(new Integer(buyNum));			
		shopingorderitemDao.addShopingorderitem(soi);
		return "success";
	}

	public String deleteShopingorderitem() throws IOException {
		String id = request.getParameter("deleteShopingorderitemID");
		Shopingorderitem soi = shopingorderitemDao.findShopingorderitemById(new Integer(id));
		shopingorderitemDao.deleteShopingorderitem(soi);
		
		return "success";
	}
}
