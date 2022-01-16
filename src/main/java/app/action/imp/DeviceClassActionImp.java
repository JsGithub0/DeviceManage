package app.action.imp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.DeviceClassDao;
import org.model.Deviceclass;

import app.action.DeviceClassAction;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class DeviceClassActionImp extends ActionSupport implements
		DeviceClassAction, ServletRequestAware, ServletResponseAware {
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

	private DeviceClassDao deviceClassDao;

	public void setDeviceClassDao(DeviceClassDao deviceClassDao) {
		this.deviceClassDao = deviceClassDao;
	}

	public void makeJson(List<Deviceclass> list) throws IOException {
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// ����json����ַ���
		JSONArray jsonArray = new JSONArray();
		for (Deviceclass dc : list) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("DeviceClassID", dc.getDeviceClassId());
			jsonObj.put("DeviceClassName", dc.getDeviceClassName());
			jsonArray.add(jsonObj);
		}
		System.out.println(jsonArray.toString());
		JSONObject root = new JSONObject();
		root.put("result", jsonArray);
		out.write(root.toString());
		out.flush();
		out.close();
	}

	/* app�˵���URL��http://127.0.0.1:8080/DeviceManage/findAllDeviceClass��ʱ���� */
	public void findAllDeviceClass() throws IOException {
		List<Deviceclass> list = deviceClassDao.findAllDeviceClass();
		makeJson(list);
	}

	/*
	 * app�˵���URL��http://127.0.0.1:8080/DeviceManage/findDeviceClass?deviceClassId=1��ʱ����ͨ��deviceClassId=1���ݲ�������ͨ��request�����getParameter�������Եõ�����deviceClassId��ֵΪ1
	 */
	public void findDeviceClass() throws IOException {
		String id = request.getParameter("deviceClassId");
		Deviceclass dc = deviceClassDao.findDeviceClass(new Integer(id));
		List<Deviceclass> list = new ArrayList<Deviceclass>();
		list.add(dc);

		makeJson(list);
	}

	public String addDeviceClass() throws IOException{
		//String deviceClassId=request.getParameter("deviceClassId");
		String deviceClassName=request.getParameter("deviceClassName");
		Deviceclass dc=new Deviceclass();
		//dc.setDeviceClassId(new Integer(deviceClassId));
		dc.setDeviceClassName(deviceClassName);
		deviceClassDao.addDeviceClass(dc);
		return "success";
	}

	public String deleteDeviceClass() throws IOException{
		String deviceClassId=request.getParameter("deviceClassId");
		Deviceclass dc=deviceClassDao.findDeviceClass(new Integer(deviceClassId));
		deviceClassDao.deleteDeviceClass(dc);
		return "success";
	}

	public String updateDeviceClass() throws IOException{
		String deviceClassId=request.getParameter("deviceClassId");
		String deviceClassName=request.getParameter("deviceClassName");
		Deviceclass dc=deviceClassDao.findDeviceClass(new Integer(deviceClassId));
		dc.setDeviceClassName(deviceClassName);
		deviceClassDao.updateDeviceClass(dc);

		return "success";
	}

}
