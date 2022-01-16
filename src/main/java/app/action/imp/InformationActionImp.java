package app.action.imp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.InformationDao;
import org.model.Information;

import app.action.DeviceClassAction;
import app.action.InformationAction;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class InformationActionImp  extends ActionSupport implements 
	InformationAction, ServletRequestAware, ServletResponseAware{
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

	private InformationDao informationDao;	
	public void setInformationDao(InformationDao informationDao) {
		this.informationDao = informationDao;
	}
	public void makeJson(List<Information> list) throws IOException{
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 构造json输出字符串
		JSONArray jsonArray = new JSONArray();
		for(Information info: list){
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("InformationID", info.getInformationId());
			jsonObj.put("InformationContent", info.getInformationContent());
			jsonObj.put("InformationImage", info.getInformationImage());
			jsonObj.put("InformationCreateTime", info.getInformationCreateTime());
			jsonArray.add(jsonObj);
		}
		System.out.println(jsonArray.toString());
		JSONObject root = new JSONObject();
		root.put("result", jsonArray);
		out.write(root.toString());
		out.flush();
		out.close();
	}

	public void findAllInformation() throws IOException {
		List<Information> list = informationDao.findAllInformation();
		makeJson(list);
	}

	// 在app中图文混排显示指定编号的咨询
	public void findInformationById() throws IOException {
		String id = request.getParameter("infoId");		
		Information info = informationDao.findInformationById(new Integer(id));
		List<Information> list = new ArrayList<Information>();
		list.add(info);
		
		makeJson(list);	
	}
	
	// 在web网页端上图文混排显示指定编号的咨询
	public void showInformationByIdFromWebPortol() throws IOException {
		String infoId = request.getParameter("infoId");
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Information information = informationDao.findInformationById(new Integer(infoId));
		if (information != null) {
			String[] array = information.getInformationContent().split("<x>");
			String richtext = "<html><body><head>"	 
					+"<meta name='viewport' content='width=device-width, initial-scale=1.0, inimum-scale=0.5,maximum-scale=2.0,user-scalable=yes'/>"
					+"<style>img{max-width:100%;height:auto;}</style></head>"
					+"<div class='text' style='text- align:center;font-size:35px'><strong>"
					+ array[1]
					+ "</strong></div>"
					+"<div class='text' style='text-align:center'>"
					+"<img src=\"http://localhost:8080" + information.getInformationImage() + "\"/>"
					+ "</div>"
					+"<div class='text' style='text-align:right'>"
					+ information.getInformationCreateTime()
					+ "</div>"
					+ "<div class='text' style='text-align:center;font-size:20px;font-style: italic;'><p><strong>"
					+ array[2] + "</strong></p></div>" + "<p>" 
					+ array[3] + "</p></body></html>";
			out.write(richtext);
			out.flush();
			out.close();
		}		
	}
	
	// 在移动app端上图文混排显示指定编号的咨询
	// array[1]是标题，array[2]是标题图片，array[3]是正文内容
	// 在移动app端上显示图片时，必须省略array[3]正文内容中的http://localhost:8080，否则无法显示图片
	public void showInformationByIdFromAppPortol() throws IOException {
		String infoId = request.getParameter("infoId");
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Information information = informationDao.findInformationById(new Integer(infoId));
		if (information != null) {
			String[] array = information.getInformationContent().split("<x>");			
			// 在移动app端上显示图片时，必须省略array[3]正文内容中的http://localhost:8080，否则无法显示图片
			String str = array[3];
			array[3] = str.replace("http://localhost:8080", "");	
			String richtext = "<html><body><head>"	 
					+"<meta name='viewport' content='width=device-width, initial-scale=1.0, inimum-scale=0.5,maximum-scale=2.0,user-scalable=yes'/>"
					+"<style>img{max-width:100%;height:auto;}</style></head>"
					+"<div class='text' style='text- align:center;font-size:35px'><strong>"
					+ array[1]
					+ "</strong></div>"
					+"<div class='text' style='text-align:center'>"
					+"<img src=\"" + information.getInformationImage() + "\"/>"
					+ "</div>"
					+"<div class='text' style='text-align:right'>"
					+ information.getInformationCreateTime()
					+ "</div>"
					+ "<div class='text' style='text-align:center;font-size:20px;font-style: italic;'><p><strong>"
					+ array[2] + "</strong></p></div>" + "<p>" 
					+ array[3] + "</p></body></html>";
			out.write(richtext);
			out.flush();
			out.close();
		}		
	}
}
