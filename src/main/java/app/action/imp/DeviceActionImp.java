package app.action.imp;

import app.action.DeviceAction;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.DeviceDao;
import org.model.Device;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DeviceActionImp extends ActionSupport implements DeviceAction,
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

	private DeviceDao deviceDao;

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public void makeJson(List<Device> list) throws IOException {
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// ����json����ַ���
		JSONArray jsonArray = new JSONArray();
		for (Device dev : list) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("DeviceID", dev.getDeviceId());
			// �豸���Ҫͨ��Deviceclass�Ӷ����ӻ��
			jsonObj.put("DeviceClassId", dev.getDeviceclass()
					.getDeviceClassId());
			jsonObj.put("DeviceName", dev.getDeviceName());
			jsonObj.put("DevicePrice", dev.getDevicePrice());
			jsonArray.add(jsonObj);
		}
		System.out.println(jsonArray.toString());
		JSONObject root = new JSONObject();
		root.put("result", jsonArray);
		out.write(root.toString());
		out.flush();
		out.close();
	}

	/* app�˵���URL��http://127.0.0.1:8080/DeviceManage/findAllDevice��ʱ���� */
	public void findAllDevice() throws IOException {
		List<Device> list = deviceDao.findAllDevice();
		makeJson(list);
	}

	/*
	 * app�˵���URL��http://127.0.0.1:8080/DeviceManage/findDeviceByDeviceClassId?
	 * deviceClassId=1��ʱ����ͨ��deviceClassId=1���ݲ�����
	 * ��ͨ��request�����getParameter�������Եõ�����deviceClassId��ֵΪ1
	 */
	public void findDeviceByDeviceClassId() throws IOException {
		String deviceClassId = request.getParameter("deviceClassId");
		List<Device> list = deviceDao.findDeviceByDeviceClassId(new Integer(
				deviceClassId));
		makeJson(list);
	}

	/*
	 * app�˵���URL��http://127.0.0.1:8080/DeviceManage/findDeviceById?deviceId=1��ʱ��
	 * ��ͨ��deviceId=1���ݲ�������ͨ��request�����getParameter�������Եõ�����deviceClassId��ֵΪ1
	 */
	public void findDeviceById() throws IOException {
		String deviceId = request.getParameter("deviceId");
		Device dev = deviceDao.findDeviceById(new Integer(deviceId));
		List<Device> list = new ArrayList<Device>();
		list.add(dev);
		makeJson(list);
	}

	/*
	 * app�˵���URL��http://127.0.0.1:8080/DeviceManage/findDeviceByName?deviceName=��ӡ��ʱ����ͨ��deviceName=��ӡ���ݲ�������ͨ��request�����getParameter�������Եõ�����deviceName��ֵΪ����ӡ
	 */
	public void findDeviceByName() throws IOException {
		String deviceName = request.getParameter("deviceName");
		// ����������룬�����Ӳ���������ת�룬�������ǲ���Ҫ��������ת��
		//deviceName = new String(deviceName.getBytes("ISO-8859-1"), "UTF-8");
		List<Device> list = deviceDao.findDeviceByName(deviceName);
		makeJson(list);
	}

	/*
	 * app�˵���URL��http://127.0.0.1:8080/DeviceManage/findDeviceByPrice?low=5&high=100��ʱ����ͨ��low=5&high=100���ݲ�������ͨ��request�����getParameter�������Եõ�����low��ֵΪ5������high��ֵΪ100
	 */
	public void findDeviceByPrice() throws IOException {
		String low = request.getParameter("low");
		String high = request.getParameter("high");
		List<Device> list = deviceDao.findDeviceByPrice(low, high);
		makeJson(list);
	}

	/*
	 * app�˵���URL��http://127.0.0.1:8080/DeviceManage/findDeviceByFuzzy?deviceClassName=�칫&deviceName=��ӡ&low=1000&high=3000��ʱ����ͨ��deviceClassName=�칫&deviceName=��ӡ&low=1000&high=3000���ݲ�������ͨ��request�����getParameter�������Եõ�����deviceClassName��ֵΪ�칫������deviceName��ֵΪ��ӡ������low��ֵΪ1000������high��ֵΪ3000
	 */
	public void findDeviceByFuzzy() throws IOException {
		String deviceClassName = request.getParameter("deviceClassName");
		String deviceName = request.getParameter("deviceName");
		String low = request.getParameter("low");
		String high = request.getParameter("high");
		// ����������룬�����Ӳ���������ת�룬�������ǲ���Ҫ��������ת��
		//deviceClassName = new String(deviceClassName.getBytes("ISO-8859-1"),"UTF-8");
		//deviceName = new String(deviceName.getBytes("ISO-8859-1"), "UTF-8");
		List<Device> list = deviceDao.findDeviceByFuzzy(deviceClassName,
				deviceName, low, high);
		makeJson(list);
	}

	public void updateDeviceById() throws IOException {
		String deviceId = request.getParameter("deviceId");
		String deviceName = request.getParameter("deviceName");
		String devicePrice = request.getParameter("devicePrice");
		deviceDao.updateDeviceById(new Integer(deviceId), deviceName, devicePrice);
		// �����Ѿ��޸ĺ���豸
		Device dev = deviceDao.findDeviceById(new Integer(deviceId));
		List<Device> list = new ArrayList<Device>();
		list.add(dev);
		makeJson(list);
	}

}
